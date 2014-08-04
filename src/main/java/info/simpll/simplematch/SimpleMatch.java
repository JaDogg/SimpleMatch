package info.simpll.simplematch;

/**
 * simple pattern matcher : match * -> one or more of any , ? -> single any
 *
 * @author Bhathiya Perera
 */
public class SimpleMatch implements Match{

    private static enum State {

        JUST_STARTED, NORMAL, EAGER, END
    }

    private final int pl; // pattern length
    private final int pob; // pattern out bound
    private final int sl; // string length
    private final int sob; // string out bound
    private final String p; // pattern
    private final String s; // string to match

    private static final char MATCH_ALL = '*';
    private static final char MATCH_ONE = '?';

    private int pp; // position of pattern
    private int ps; // position of string
    private State z; // state
    private boolean m = false; // is match

    public SimpleMatch(String p, String s) {

        if (p == null || s == null) {
            throw new IllegalArgumentException(
                    "Pattern and String must not be null");
        }

        this.p = p;
        this.s = s;
        pl = p.length();
        sl = s.length();
        if (pl == 0 || sl == 0) {
            throw new IllegalArgumentException(
                    "Pattern and String must have at least one character");
        }
        pob = pl - 1;
        sob = sl - 1;
        pp = 0;
        ps = 0;
        z = State.JUST_STARTED;

    }

    public SimpleMatch(String p, String s, int pp, int ps) {

        this(p, s);
        this.pp = pp;
        this.ps = ps;
    }

    private void calcState() {
        //calculate state
        if (z == State.END) {
            return;
        }

        if (!psafe() || !ssafe()) {
            z = State.END;
        } else if (pc() == MATCH_ALL) {
            if (!pnsafe()) {
                z = State.END;
                m = true;
            } else {
                z = State.EAGER;
            }
        } else {
            z = State.NORMAL;
        }
    }

    private void eat() {
        //eat a character
        if (z == State.END) {
            return;
        }
        
        m = false;
        
        if (z == State.EAGER) {
            SimpleMatch smo = new SimpleMatch(p, s, pp + 1, ps + 1);
            if (smo.match()) {
                z = State.END;
                m = true;
                return;
            }
            ips();
        } else if (z == State.NORMAL) {
            if (mo()) {
                ips();
                ipp();
                m = true;
            } else {
                z = State.END;
                m = false;
            }
        }
    }

    private boolean mo() {
        // match one
        char pc = pc();
        return (pc == MATCH_ONE || pc == sc());
    }

    private char pc() {
        // pattern current char
        return p.charAt(pp);
    }

    private char sc() {
        // str current char
        return s.charAt(ps);
    }

    private boolean psafe() {
        //pattern position bound check
        return pp <= pob;
    }

    private boolean pnsafe() {
        //pattern next position bound check
        return (pp + 1) <= pob;
    }

    private boolean ssafe() {
        //string bound check
        return ps <= sob;
    }

    private void ipp() {
        //increase position of pattern
        pp++;
    }

    private void ips() {
        //increate position of string
        ps++;
    }

    /**
     * Match and return result
     * @return true if match
     */
    @Override
    public boolean match() {
        if (pob > sob) {
            return false;
        }
        while (z != State.END) {
            calcState();
            eat();
        }
        return m;
    }

    /**
     * Match and return result
     * @param p pattern
     * @param s string to match
     * @return true if match
     * @throws IllegalArgumentException 
     */
    public static boolean match(String p, String s) throws
            IllegalArgumentException {
        return new SimpleMatch(p, s).match();
    }

}

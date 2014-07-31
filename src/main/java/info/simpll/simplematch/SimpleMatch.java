package info.simpll.simplematch;

/**
 * simple pattern matcher : match * -> one or more of any , ? -> single any
 *
 * @author Bhathiya Perera
 */
public class SimpleMatch {

    private static enum State {

        JUST_STARTED, NORMAL, EAGER, END
    }

    private final int pl;
    private final int pob;
    private final int sl;
    private final int sob;
    private final String p;
    private final String s;

    private static final char MATCH_ALL = '*';
    private static final char MATCH_ONE = '?';

    private int pp;
    private int ps;
    private State z;
    private boolean m = false;

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
        char pc = pc();
        return (pc == MATCH_ONE || pc == sc());
    }

    private boolean mn() {
        return (pn() == sc());
    }

    private char pc() {
        return p.charAt(pp);
    }

    private char pn() {
        return p.charAt(pp + 1);
    }

    private char sc() {
        return s.charAt(ps);
    }

    private boolean psafe() {
        return pp <= pob;
    }

    private boolean pnsafe() {
        return (pp + 1) <= pob;
    }

    private boolean ssafe() {
        return ps <= sob;
    }

    private void ipp() {
        pp++;
    }

    private void ips() {
        ps++;
    }

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

    public static boolean match(String p, String s) throws
            IllegalArgumentException {
        return new SimpleMatch(p, s).match();
    }

}

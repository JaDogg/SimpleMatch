package info.simpll.simplematch;

/**
 * simple pattern matcher : match * -> one or more of any , ? -> single any
 *
 * @author Bhathiya Perera
 */
public class SimpleMatch implements Match {

    //state enums
    private static enum State {

        JUST_STARTED, NORMAL, EAGER, END
    }

    //constants
    private static final char MATCH_ALL = '*';
    private static final char MATCH_ONE = '?';

    private final int ptnOutBound; // pattern out bound
    private final int strOutBound; // string out bound
    private final String pattern; // pattern
    private final String matchString; // string to match

    private int ptnPosition; // position of pattern
    private int strPosition; // position of string
    private State state = State.JUST_STARTED; // state
    private boolean matchFound = false; // is match

    public SimpleMatch(String pattern, String matchStr) {

        if (pattern == null || matchStr == null) {
            throw new IllegalArgumentException(
                    "Pattern and String must not be null");
        }

        this.pattern = pattern;
        this.matchString = matchStr;
        int pl = pattern.length();
        int sl = matchStr.length();
        if (pl == 0 || sl == 0) {
            throw new IllegalArgumentException(
                    "Pattern and String must have at least one character");
        }
        ptnOutBound = pl - 1;
        strOutBound = sl - 1;
        ptnPosition = 0;
        strPosition = 0;

    }

    private void calcState() {
        //calculate state
        if (state == State.END) {
            return;
        }

        if (!patternCheckBound() || !matchStrCheckBound()) {
            state = State.END;
        } else if (patternChar() == MATCH_ALL) {
            if (!patternNextCheckBound()) {
                state = State.END;
                matchFound = true;
            } else {
                state = State.EAGER;
            }
        } else {
            state = State.NORMAL;
        }
    }

    private void eat() {
        //eat a character
        if (state == State.END) {
            return;
        }

        matchFound = false;

        if (state == State.EAGER) {

            int curStrPosition = strPosition;
            int curPtnPosition = ptnPosition;
            strPosition++;
            ptnPosition++;
            if (match()) {
                state = State.END;
                matchFound = true;
                return;
            } else {
                strPosition = curStrPosition;
                ptnPosition = curPtnPosition;
                state = State.EAGER;
            }
            strPosition++;
        } else if (state == State.NORMAL) {
            if (matchOne()) {
                strPosition++;
                ptnPosition++;
                matchFound = true;
            } else {
                state = State.END;
            }
        }
    }

    private boolean matchOne() {
        // match one
        char pc = patternChar();
        return (pc == MATCH_ONE || pc == matchStrChar());
    }

    private char patternChar() {
        // pattern current char
        return pattern.charAt(ptnPosition);
    }

    private char matchStrChar() {
        // str current char
        return matchString.charAt(strPosition);
    }

    private boolean patternCheckBound() {
        //pattern position bound check
        return ptnPosition <= ptnOutBound;
    }

    private boolean patternNextCheckBound() {
        //pattern next position bound check
        return (ptnPosition + 1) <= ptnOutBound;
    }

    private boolean matchStrCheckBound() {
        //string bound check
        return strPosition <= strOutBound;
    }

    /**
     * Match and return result
     *
     * @return true if match
     */
    @Override
    public boolean match() {
        if (ptnOutBound > strOutBound) {
            return false;
        }
        while (state != State.END) {
            calcState();
            eat();
        }
        return matchFound;
    }

    /**
     * Match and return result
     *
     * @param pattern pattern
     * @param matchStr string to match
     * @return true if match
     * @throws IllegalArgumentException
     */
    public static boolean match(String pattern, String matchStr) throws
            IllegalArgumentException {
        return new SimpleMatch(pattern, matchStr).match();
    }

}

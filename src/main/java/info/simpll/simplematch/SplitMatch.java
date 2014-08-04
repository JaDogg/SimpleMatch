package info.simpll.simplematch;

import java.util.ArrayList;

/**
 * Split both pattern and string by given char and match each part using
 * SimpleMatch
 *
 * @author Bhathiya
 */
public class SplitMatch implements Match {

    private final String s;
    private final String p;
    private final int pl;
    private final int sl;

    private String[] sa;
    private String[] pa;

    /**
     * SplitMatch
     *
     * @param p pattern , delimiter = c
     * @param s string , delimiter = c
     * @param c char to split from
     */
    public SplitMatch(String p, String s, char c) {

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
        sa = split(s, c);
        pa = split(p, c);

    }

    private String[] split(String s, char c) {
        //split string s, using char c
        ArrayList<String> arr = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char at = s.charAt(i);
            if (at == c) {
                arr.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(at);
            }
        }
        return arr.toArray(new String[0]);
    }

    private boolean splitMatch() {
        if (pl > sl || pa.length != sa.length) {
            return false;
        }
        for (int i = 0; i < sa.length; i++) {
            if (sa[i].length() == 0 && pa[i].length() == 0) {
            } else if (!SimpleMatch.match(pa[i], sa[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Match and return result
     *
     * @return true if match
     */
    @Override
    public boolean match() {
        boolean sim = SimpleMatch.match(p, s); //if direct match
        return (sim && splitMatch());
    }

    /**
     * Match and return result
     * @param p pattern , delimiter = c
     * @param s string to match , delimiter = c
     * @param c char to split from 
     * @return true if match
     * @throws IllegalArgumentException 
     */
    public static boolean match(String p, String s, char c) throws
            IllegalArgumentException {
        return new SplitMatch(p, s, c).match();
    }
}

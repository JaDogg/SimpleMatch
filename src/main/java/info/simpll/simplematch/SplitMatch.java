package info.simpll.simplematch;

import java.util.ArrayList;

/**
 * Split both pattern and string by given char and match each part using
 * SimpleMatch
 *
 * @author Bhathiya
 */
public class SplitMatch implements Match {

    private final String matchStr;
    private final String pattern;
    private final int ptnLength;
    private final int matchStrLength;
    private String[] matchStrParts;
    private String[] patternParts;

    /**
     * SplitMatch
     *
     * @param pattern pattern , delimiter = c
     * @param matchStr string , delimiter = c
     * @param delimiter char to split from
     */
    public SplitMatch(String pattern, String matchStr, char delimiter) {

        if (pattern == null || matchStr == null) {
            throw new IllegalArgumentException(
                    "Pattern and String must not be null");
        }

        this.pattern = pattern;
        this.matchStr = matchStr;
        ptnLength = pattern.length();
        matchStrLength = matchStr.length();
        if (ptnLength == 0 || matchStrLength == 0) {
            throw new IllegalArgumentException(
                    "Pattern and String must have at least one character");
        }
        matchStrParts = split(matchStr, delimiter);
        patternParts = split(pattern, delimiter);

    }

    private String[] split(String strToSplit, char delimiter) {
        //split string s, using char c
        ArrayList<String> arr = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strToSplit.length(); i++) {
            char at = strToSplit.charAt(i);
            if (at == delimiter) {
                arr.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(at);
            }
        }
        return arr.toArray(new String[0]);
    }

    private boolean splitMatch() {
        if (ptnLength > matchStrLength || patternParts.length
                != matchStrParts.length) {
            return false;
        }
        for (int i = 0; i < matchStrParts.length; i++) {
            if (matchStrParts[i].length() == 0 && patternParts[i].length() == 0) {
            } else if (!SimpleMatch.match(patternParts[i], matchStrParts[i])) {
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
        boolean sim = SimpleMatch.match(pattern, matchStr); //if direct match
        return (sim && splitMatch());
    }

    /**
     * Match and return result
     *
     * @param pattern pattern , delimiter = c
     * @param matchStr string to match , delimiter = c
     * @param delimiter char to split from
     * @return true if match
     * @throws IllegalArgumentException
     */
    public static boolean match(String pattern, String matchStr, char delimiter)
            throws
            IllegalArgumentException {
        return new SplitMatch(pattern, matchStr, delimiter).match();
    }
}

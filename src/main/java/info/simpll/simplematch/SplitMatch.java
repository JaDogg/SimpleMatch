/*
 * The MIT License
 *
 * Copyright 2014 Bhathiya.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.simpll.simplematch;

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
        matchStrParts = StringUtility.split(matchStr, delimiter);
        patternParts = StringUtility.split(pattern, delimiter);

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

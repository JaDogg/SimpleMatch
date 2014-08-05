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

import java.util.LinkedList;
import java.util.Queue;

/**
 * FiFo based match list This class will match given string to <br />
 * each pattern assigned to it in the order of a queue, <br />
 * when ever a match occur it will return the associated name
 *
 * @param <T> type of name object
 *
 * @author Bhathiya
 */
public class NamedMatchList<T> {

    private static class NamedPattern<T> {

        private final T identifier;
        private final String pattern;

        public NamedPattern(T name, String pattern) {
            this.identifier = name;
            this.pattern = pattern;
        }

        public T getIdentifier() {
            return identifier;
        }

        public String getPattern() {
            return pattern;
        }

    }

    private final Queue<NamedPattern<T>> queue;

    public NamedMatchList() {
        queue = new LinkedList<NamedPattern<T>>();
    }

    /**
     * add a pattern to list
     *
     * @param identifier
     * @param pattern
     */
    public void add(T identifier, String pattern) {
        queue.add(new NamedPattern(identifier, pattern));
    }

    /**
     * match and return identifier;
     *
     * @param matchStr string to consider
     * @return identifier of pattern if matched else null;
     */
    public T match(String matchStr) {
        for (NamedPattern<T> m : queue) {
            if (SimpleMatch.match(m.getPattern(), matchStr)) {
                return m.getIdentifier();
            }
        }
        return null;
    }
}

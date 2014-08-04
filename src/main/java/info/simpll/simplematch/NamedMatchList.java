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
     * @param identifier
     * @param pattern 
     */
    public void add(T identifier, String pattern) {
        queue.add(new NamedPattern(identifier, pattern));
    }

    /**
     * match and return identifier;
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

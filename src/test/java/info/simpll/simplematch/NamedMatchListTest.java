package info.simpll.simplematch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit Test for named Match List
 *
 * @author Bhathiya
 */
public class NamedMatchListTest {

    @Test
    public void test1() {
        NamedMatchList<String> ml = new NamedMatchList<String>();

        String i1 = "all in x/y/z/";
        String i2 = "all /z/abc.jpg in /x/";
        String i3 = "all in x";
        String i4 = "all in root";
        String i5 = "unknown";
        //matching set of paths
        String[][] patterns = {
            {i1, "/x/y/z/*"},
            {i2, "/x/*/z/abc.jpg"},
            {i3, "/x/*"},
            {i4, "/*"},
            {i5, "*"}
        };

        for (String[] pat : patterns) {
            ml.add(pat[0], pat[1]);
        }

        String[][] paths = {
            {i1, "/x/y/z/file.png"},
            {i2, "/x/abba/z/abc.jpg"},
            {i3, "/x/n/z/file.png"},
            {i4, "/abc/"}};

        for (String[] path : paths) {
            Assert.assertTrue(path[0].equals(ml.match(path[1])));
        }

    }
}

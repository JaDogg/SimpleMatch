package info.simpll.simplematch;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Bhathiya
 */
public class SimpleMatchTest {

    @Test
    public void test1() {
        SimpleMatch m1 = new SimpleMatch("a*", "bb");
        Assert.assertFalse(m1.match());
    }

    @Test
    public void test2() {
        SimpleMatch m2 = new SimpleMatch("a*b", "anj");
        Assert.assertFalse(m2.match());
    }

    @Test
    public void test3() {
        SimpleMatch m3 = new SimpleMatch("a?b", "acd");
        Assert.assertFalse(m3.match());
    }

    @Test
    public void test4() {
        SimpleMatch m4 = new SimpleMatch("a*", "abcdefg");
        Assert.assertTrue(m4.match());
    }

    @Test
    public void test5() {
        SimpleMatch m5 = new SimpleMatch("a*ba", "abba");
        Assert.assertTrue(m5.match());

    }

    @Test
    public void test6() {
        SimpleMatch m6 = new SimpleMatch("bhathiya", "bhathiya");
        Assert.assertTrue(m6.match());
    }

    @Test
    public void test7() {
        SimpleMatch m7 = new SimpleMatch("a?", "a1");
        Assert.assertTrue(m7.match());
    }

    @Test
    public void test8() {
        SimpleMatch m8 = new SimpleMatch("bhathiya", "blah");
        Assert.assertFalse(m8.match());
    }

    @Test
    public void test9() {
        SimpleMatch m9 = new SimpleMatch("/img/abc.jpg", "/img/abc.jpg");
        Assert.assertTrue(m9.match());
    }

    @Test
    public void test11() {
        SimpleMatch m9 = new SimpleMatch("/x/*/z/abc.jpg", "/x/a/z/abc.jpg");
        Assert.assertTrue(m9.match());
    }

    @Test
    public void test12() {
        SimpleMatch m9 = new SimpleMatch("/x/*/z/abc.jpg", "/x/a/j/abc.jpg");
        Assert.assertFalse(m9.match());
    }

    @Test
    public void test13() {
        SimpleMatch m9 = new SimpleMatch("a", "a");
        Assert.assertTrue(m9.match());
    }

    @Test
    public void test14() {
        SimpleMatch m9 = new SimpleMatch("a", "b");
        Assert.assertFalse(m9.match());
    }

    @Test
    public void test15() {
        SimpleMatch m9 = new SimpleMatch("aa", "ab");
        Assert.assertFalse(m9.match());
    }

    @Test
    public void test10() {
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

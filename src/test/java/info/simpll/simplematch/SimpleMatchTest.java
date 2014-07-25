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
        SimpleMatch m2 = new SimpleMatch("a*b", "abj");
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
        SimpleMatch m6 = new SimpleMatch("avenra", "avenra");
        Assert.assertTrue(m6.match());
    }
    @Test
    public void test7() {
        SimpleMatch m7 = new SimpleMatch("a?", "a1");
        Assert.assertTrue(m7.match());
    }
}

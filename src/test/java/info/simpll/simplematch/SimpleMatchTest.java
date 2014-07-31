package info.simpll.simplematch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit Test for SimpleMatch
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

}

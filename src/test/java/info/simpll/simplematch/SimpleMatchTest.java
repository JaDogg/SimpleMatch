package info.simpll.simplematch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit Test for SimpleMatch
 *
 * @author Bhathiya
 */
public class SimpleMatchTest {

    @Test
    public void test1() {
        SimpleMatch m = new SimpleMatch("a*", "bb");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test2() {
        SimpleMatch m = new SimpleMatch("a*b", "anj");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test3() {
        SimpleMatch m = new SimpleMatch("a?b", "acd");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test4() {
        SimpleMatch m = new SimpleMatch("a*", "abcdefg");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test5() {
        SimpleMatch m = new SimpleMatch("a*ba", "abba");
        Assert.assertTrue(m.match());

    }

    @Test
    public void test6() {
        SimpleMatch m = new SimpleMatch("bhathiya", "bhathiya");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test7() {
        SimpleMatch m = new SimpleMatch("a?", "a1");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test8() {
        SimpleMatch m = new SimpleMatch("bhathiya", "blah");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test9() {
        SimpleMatch m = new SimpleMatch("/img/abc.jpg", "/img/abc.jpg");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test11() {
        SimpleMatch m = new SimpleMatch("/x/*/z/abc.jpg", "/x/a/z/abc.jpg");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test12() {
        SimpleMatch m = new SimpleMatch("/x/*/z/abc.jpg", "/x/a/j/abc.jpg");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test13() {
        SimpleMatch m = new SimpleMatch("a", "a");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test14() {
        SimpleMatch m = new SimpleMatch("a", "b");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test15() {
        SimpleMatch m = new SimpleMatch("aa", "ab");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test16() {
        SimpleMatch m = new SimpleMatch("aaaa", "a");
        Assert.assertFalse(m.match());
        m = new SimpleMatch("aaaa", "aaa");
        Assert.assertFalse(m.match());
        m = new SimpleMatch("aaaa", "aa");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test17() {
        SimpleMatch m = new SimpleMatch("a*", "a");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test18() {
        SimpleMatch m = new SimpleMatch("a*xxxxxxxxxxb", "axxxxxxxxxxxb");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test19() {
        SimpleMatch m = new SimpleMatch("a*xxxxxxxxxxb", "axxxxxxxxxxxc");
        Assert.assertFalse(m.match());
    }

    @Test
    public void test20() {
        SimpleMatch m = new SimpleMatch("a*b*c*d", "abbccdd");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test21() {
        SimpleMatch m = new SimpleMatch("a*xxxxxxx*xxb", "axxxxxxxxxhelloxxb");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test22() {
        SimpleMatch m = new SimpleMatch("a*xxxxxxxxxx*", "axxxxxxxxxxxhello");
        Assert.assertTrue(m.match());
    }

    @Test
    public void test23() {
        SimpleMatch m = new SimpleMatch(
                "start*in-part-1*in-part-2*in-part-3*end",
                "start[because]in-part-1[I'm]in-part-2[Batman]in-part-3[!]end");
        Assert.assertTrue(m.match());
    }
}

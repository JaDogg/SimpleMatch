package info.simpll.simplematch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit Test for SplitMatch Class
 *
 * @author Bhathiya
 */
public class SplitMatchTest {

    @Test
    public void test1() {
        Assert.assertTrue(SplitMatch.match("same", "same", '/'));
    }

    @Test
    public void test2() {
        Assert.
                assertFalse(SplitMatch.
                        match("/img/abc.jpg", "/img/xyz.jpg", '/'));
    }

    @Test
    public void test3() {
        Assert.assertTrue(SplitMatch.match("/x/*/z/abc.jpg", "/x/a/z/abc.jpg",
                '/'));
    }

    @Test
    public void test4() {
        Assert.assertFalse(SplitMatch.match("/x/*/z/abc.jpg", "/x/a/j/abc.jpg",
                '/'));
    }
}

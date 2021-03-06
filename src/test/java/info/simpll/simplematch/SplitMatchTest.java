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

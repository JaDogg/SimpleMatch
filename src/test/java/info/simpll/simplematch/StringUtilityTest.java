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
 *
 * @author Bhathiya
 */
public class StringUtilityTest {

    @Test
    public void test1() {
        final String str1 = "Because";
        final String str2 = "I'm";
        final String str3 = "Batman";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1 + delim + str2 + delim + str3,
                delim);
        Assert.assertTrue(parts.length == 3);
        Assert.assertTrue(parts[0].equals(str1));
        Assert.assertTrue(parts[1].equals(str2));
        Assert.assertTrue(parts[2].equals(str3));
    }

    @Test
    public void test2() {
        final String str1 = "";
        final String str2 = "I'm";
        final String str3 = "Batman";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1 + delim + str2 + delim + str3,
                delim);
        Assert.assertTrue(parts.length == 3);
        Assert.assertTrue(parts[0].equals(str1));
        Assert.assertTrue(parts[1].equals(str2));
        Assert.assertTrue(parts[2].equals(str3));
    }

    @Test
    public void test3() {
        final String str1 = "";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1,
                delim);
        Assert.assertTrue(parts.length == 1);
        Assert.assertTrue(parts[0].equals(str1));
    }
}

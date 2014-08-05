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

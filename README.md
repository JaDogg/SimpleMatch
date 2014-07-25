SimpleMatch 
===========

Simple Pattern Matcher for Java 
[![Build Status](https://api.travis-ci.org/JaDogg/SimpleMatch.svg?branch=master)](https://travis-ci.org/JaDogg/SimpleMatch)

What it does ?
---
1. Matches a `?` for any single character
1. Matches an `*` for any character

How to use ?
---
1. `SimpleMatch.match("a*","abcd");` or
2. `SimpleMatch m = new SimpleMatch("a*", "abcdefg");` and call `m.match();`

More info
---
3. It will either return `true` or `false` or throw an `IllegalArgumentException`
4. `IllegalArgumentException` occurs if any parameter is `null` or `length==0` 
5. Always false on `pattern length > string length`

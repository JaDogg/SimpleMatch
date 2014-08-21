SimpleMatch 
===========

Simple Pattern Matcher for Java 

[![Build Status](https://travis-ci.org/JaDogg/SimpleMatch.png?branch=master)](https://travis-ci.org/JaDogg/SimpleMatch)

What does it do?
---
1. Matches a `?` for any single character
1. Matches an `*` for one or more characters

How to use ?
---
1. `SimpleMatch.match("a*","abcd");` or
2. `SimpleMatch m = new SimpleMatch("a*", "abcdefg");` and call `m.match();`

More info
---
3. It will either return `true` or `false` or throw an `IllegalArgumentException`
4. `IllegalArgumentException` occurs if any parameter is `null` or `length==0` 
5. Always false on `pattern length > string length`

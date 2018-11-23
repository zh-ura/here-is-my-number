package com.github.zhura.mynumber.util

import org.scalatest.FunSuite

class WordUtilsTest extends FunSuite {
  test("wordToDigital converts a, b, c to 2") {
    assert(WordUtils.wordToDigital("a") === "2")
    assert(WordUtils.wordToDigital("b") === "2")
    assert(WordUtils.wordToDigital("c") === "2")
  }

  test("wordToDigital converts d, e, f to 3") {
    assert(WordUtils.wordToDigital("d") === "3")
    assert(WordUtils.wordToDigital("e") === "3")
    assert(WordUtils.wordToDigital("f") === "3")
  }

  test("wordToDigital converts g, h, i to 4") {
    assert(WordUtils.wordToDigital("g") === "4")
    assert(WordUtils.wordToDigital("h") === "4")
    assert(WordUtils.wordToDigital("i") === "4")
  }

  test("wordToDigital converts j, k, l to 5") {
    assert(WordUtils.wordToDigital("j") === "5")
    assert(WordUtils.wordToDigital("k") === "5")
    assert(WordUtils.wordToDigital("l") === "5")
  }

  test("wordToDigital converts m, n, o to 6") {
    assert(WordUtils.wordToDigital("m") === "6")
    assert(WordUtils.wordToDigital("n") === "6")
    assert(WordUtils.wordToDigital("o") === "6")
  }

  test("wordToDigital converts p, q, r, s to 7") {
    assert(WordUtils.wordToDigital("p") === "7")
    assert(WordUtils.wordToDigital("q") === "7")
    assert(WordUtils.wordToDigital("r") === "7")
    assert(WordUtils.wordToDigital("s") === "7")
  }

  test("wordToDigital converts t, u, v to 8") {
    assert(WordUtils.wordToDigital("t") === "8")
    assert(WordUtils.wordToDigital("u") === "8")
    assert(WordUtils.wordToDigital("v") === "8")
  }

  test("wordToDigital converts w, x, y, z to 9") {
    assert(WordUtils.wordToDigital("w") === "9")
    assert(WordUtils.wordToDigital("x") === "9")
    assert(WordUtils.wordToDigital("y") === "9")
    assert(WordUtils.wordToDigital("z") === "9")
  }

  test("wordToDigital doesn't convert numbers") {
    assert(WordUtils.wordToDigital("0123") === "0123")
  }

  test("wordToDigital doesn't convert non-latin chars") {
    assert(WordUtils.wordToDigital("12л!@") === "12л!@")
  }

  test("wordToDigital is case insensitive") {
    val testData = "Hello, World!"
    assert(WordUtils.wordToDigital(testData) === WordUtils.wordToDigital(testData.toLowerCase))
    assert(WordUtils.wordToDigital(testData) === WordUtils.wordToDigital(testData.toUpperCase))
  }

  test("wordToDigital converts word to number") {
    assert(WordUtils.wordToDigital("Discover") === "34726837")
  }

}

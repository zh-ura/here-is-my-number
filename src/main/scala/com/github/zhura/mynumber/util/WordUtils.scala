package com.github.zhura.mynumber.util

/**
  * Used to convert english vocabulary words to digit sequences
  */
object WordUtils {

  /**
    * Converts latin word to phone keyboard based digital representation
    * When encountering non-latin letter, echoes it (fail silently)
    * @param word latin character word
    * @return sequence of digits
    */
  def wordToDigital(word: String): String = {
    word.toLowerCase.map(c => charToNumChar(c))
  }

  private def charToNumChar(letter: Char): Char = {
    letter match {
      case two if Vector('a', 'b', 'c').contains(two) => '2'
      case three if Vector('d', 'e', 'f').contains(three) => '3'
      case four if Vector('g', 'h', 'i').contains(four) => '4'
      case five if Vector('j', 'k', 'l').contains(five) => '5'
      case six if Vector('m', 'n', 'o').contains(six) => '6'
      case seven if Vector('p', 'q', 'r', 's').contains(seven) => '7'
      case eight if Vector('t', 'u', 'v').contains(eight) => '8'
      case nine if Vector('w', 'x', 'y', 'z').contains(nine) => '9'
      case anyOther => anyOther
    }
  }

}

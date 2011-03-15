/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

import java.util.Date
import java.text.SimpleDateFormat

object PDUData {
  /**
   * Converts a 4 octects value (Int) to bytes List
   */
  def int2Binary(value: Int): List[Int] = {
    val one = (value >> 24 & 0xff)
    val two = (value >> 16 & 0xff)
    val three = (value >> 8 & 0xff)
    val four = (value & 0xff)

    List(one, two, three, four)
  }

  /**
   * Converts a 2 octects (Short) to bytes List
   */
  def short2Binary(value: Short): List[Int] = {
    val one = (value >> 8 & 0xff)
    val two = (value & 0xff)
    List(one, two)
  }

  /**
   * Converts 1 octet to bytes List
   */
  def sshort2Binary(value: Short): List[Int] = {
    List(value & 0xff)
  }

  def binary2Int(value: List[Int]): Int = {
    (
      ((value(0) & 0xff) << 24) +
        ((value(1) & 0xff) << 16) +
        ((value(2) & 0xff) << 8) +
        (value(3) & 0xff)
      )
  }

  def binary2Short(value: List[Int]): Short = {
    (
      ((value(0) & 0xff) << 8) +
        (value(1) & 0xff)
      ).asInstanceOf[Short]
  }

  def binary2SShort(value: List[Int]): Short = {
    (value(0) & 0xff).asInstanceOf[Short]
  }

  def binary2SShort(value: Int): Short = {
    (value & 0xff).asInstanceOf[Short]
  }

  def cstring2Binary(value: String, min: Int, max: Int): List[Int] = {
    if ((value.length == max - 1) || (value.length == min)) {
      cstring2Binary(value, max)
    } else {
      throw new MatchError("Value can only be " + min + " or " + max)
    }
  }

  def cstring2Binary(value: String, max: Int): List[Int] = {
    val size = max - 1
    val data1 = value.length match {
      case n: Int if n <= size => value
      case _ => value.substring(0, size)
    }
    val response = (data1 + "\0").getBytes.toList
    for (i <- response) yield i.asInstanceOf[Int]
  }

  def string2Binary(value: String, max: Int): List[Int] = {
    val response = (value.length match {
      case n: Int if n <= max => value
      case empty: Int if empty == 0 => "\0"
      case _ => value.substring(0, max)
    }).getBytes.toList

    for (i <- response) yield i.asInstanceOf[Int]
  }

  def binary2String(value: List[Int]): String = {
    value.length match {
      case 1 if (value(0) == 0) => ""
      case _ => value.map(_.asInstanceOf[Char]).mkString
    }
  }

  private def prependZeroTo(value: Int) = {
    String.valueOf(value).length match {
      case 1 => "0" + String.valueOf(value)
      case _ => String.valueOf(value)
    }
  }

  /**
   * quarterHourSymbol can be + or -
   */
  def date2AbsoluteDate(value: Date, quarterHourSymbol: String = "+", differenceWithGMT: Int = 1): List[Int] = {
    val diffInString = prependZeroTo(differenceWithGMT)
    val dateFormatter = new SimpleDateFormat("yyMMddHHmmss0" + diffInString)
    string2Binary(dateFormatter.format(value) + quarterHourSymbol, 16)
  }

  def date2RelativeDate(yy: Int = 0, MM: Int = 0, dd: Int = 0, hh: Int = 0, mm: Int = 0, ss: Int = 0): List[Int] = {
    val date = "" +
      prependZeroTo(yy) +
      prependZeroTo(MM) +
      prependZeroTo(dd) +
      prependZeroTo(hh) +
      prependZeroTo(mm) +
      prependZeroTo(ss) + "000R"
    string2Binary(date, 16)
  }
}

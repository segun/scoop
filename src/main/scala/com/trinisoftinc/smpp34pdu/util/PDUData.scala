/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

object PDUData {
  /**
   * Converts a 4 octects value (Int) to bytes array
   */
  def int2Binary(value: Int): Array[Int] = {
    val one = (value >> 24 & 0xff)
    val two = (value >> 16 & 0xff)
    val three = (value >> 8 & 0xff)
    val four = (value & 0xff)

    Array(one,two,three,four)
  }

  /**
   * Converts a 2 octects (Short) to bytes array
   */
  def short2Binary(value: Short): Array[Int] = {
    val one = (value >> 8 & 0xff)
    val two = (value & 0xff)
    Array(one, two)
  }

  /**
   * Converts 1 octet to bytes array
   */
  def sshort2Binary(value: Short): Array[Int] = {
    Array(value & 0xff)
  }

  def binary2Int(value: Array[Int]): Int = {
    (
      ((value(0) & 0xff) << 24 ) +
      ((value(1) & 0xff) << 16) +
      ((value(2) & 0xff) << 8) +
      (value(3) & 0xff)
    )    
  }

  def binary2Short(value: Array[Int]): Short = {
    (
      ((value(0) & 0xff) << 8) +
      (value(1) & 0xff)
    ).asInstanceOf[Short]
  }

  def binary2SShort(value: Array[Int]): Short = {
    (value(0) & 0xff).asInstanceOf[Short]
  }

  def cstring2Binary(value: String, max: Int): Array[Int] = {
    val size = max - 1
    val data1 = value.length match {
        case n: Int if n <= size => value
        case _ => value.substring(0, size)
    }
    val response = (data1 + "\0").getBytes
    for(i <- response) yield i.asInstanceOf[Int]
  }

  def string2Binary(value: String, max: Int): Array[Int] = {
    val response = (value.length match {
      case n: Int if n <= max => value
      case empty: Int if empty == 0 => "\0"
      case _ => value.substring(0, max)
    }).getBytes

    for(i <- response) yield i.asInstanceOf[Int]
  }

  def binary2String(value: Array[Int]): String = {
    value.length match {
      case 1 if(value(0) == 0) => ""
      case _ => value.map(_.asInstanceOf[Char]).mkString
    }
  }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

object PDUData {
  /**
   * Converts a 4 octects value (Int) to bytes array
   */
  def int2Bytes(value: Int): Array[Byte] = {
    val one = (value >> 24 & 0xff).asInstanceOf[Byte]
    val two = (value >> 16 & 0xff).asInstanceOf[Byte]
    val three = (value >> 8 & 0xff).asInstanceOf[Byte]
    val four = (value & 0xff).asInstanceOf[Byte]

    Array(one,two,three,four)
  }

  /**
   * Converts a 2 octects (Short) to bytes array
   */
  def short2Bytes(value: Short): Array[Byte] = {
    val one = (value >> 8 & 0xff).asInstanceOf[Byte]
    val two = (value & 0xff).asInstanceOf[Byte]
    Array(one, two)
  }

  /**
   * Converts 1 octet to bytes array
   */
  def sshort2Bytes(value: Short): Array[Byte] = {
    Array((value & 0xff).asInstanceOf[Byte])
  }

  def bytes2Int(value: Array[Byte]): Int = {
    (
      ((value(0) & 0xff) << 24 ) +
      ((value(1) & 0xff) << 16) +
      ((value(2) & 0xff) << 8) +
      (value(3) & 0xff)
    )    
  }

  def bytes2Short(value: Array[Byte]): Short = {
    (
      ((value(0) & 0xff) << 8) +
      (value(1) & 0xff)
    ).asInstanceOf[Short]
  }

  def bytes2SShort(value: Array[Byte]): Short = {
    (value(0) & 0xff).asInstanceOf[Short]
  }

  def cstring2Bytes(value: String, max: Int): Array[Byte] = {
    val size = max - 1
    val data = value.length match {
        case n: Int if n <= size => value
        case _ => value.substring(0, size)
    }
    (data + "\0").getBytes
  }

  def string2Bytes(value: String, max: Int): Array[Byte] = {
    (value.length match {
      case n: Int if n <= max => value
      case empty: Int if empty == 0 => "\0"
      case _ => value.substring(0, max)
    }).getBytes
  }

  def bytes2String(value: Array[Byte]): String = {
    value.length match {
      case 1 if(value(0) == 0) => ""
      case _ => value.map(_.asInstanceOf[Char]).mkString
    }
  }
}

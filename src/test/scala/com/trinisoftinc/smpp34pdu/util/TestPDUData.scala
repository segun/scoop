/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.PDUData._

class TestPDUData extends FlatSpec with ShouldMatchers {


  "int2Binary" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(0, 0, 2, 4)
    val result = int2Binary(0x0204)
    val result2 = int2Binary(516)
    result should equal (expected)
    result2 should equal (expected)
  }

  "short2Binary" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(4, 34)
    val result = short2Binary(0x0422)
    val result2 = short2Binary(1058)
    result should equal (expected)
    result2 should equal (expected)
  }

  "binary2Int" should "give an Integer" in {
    val expected = 1058
    val result = binary2Int(Array(0, 0, 4, 34))
    result should equal (expected)
  }

  "binary2Short" should " give a Short" in {
    val expected: Short = 516
    val result = binary2Short(Array(2, 4))
    result should equal (expected)
  }

  "binary2String" should "give a String" in {
    val result = binary2String(Array(97,98,99))
    val expected = "abc"

    result should equal (expected)
  }

  it should "give an empty String" in {
    val result = binary2String(Array(0))
    val expected = ""
    result.length should equal (0)
    //expected.length should equal (0)
  }

  "sshort2bytes" should "give an Array of Bytes" in {
    val expected = Array(2)
    val result = sshort2Binary(0x2)
    result should equal (expected)
  }

  "cstring2Binary" should "gve an Array of Bytes, padded with 0" in {
    val expected: Array[Byte] = Array(111, 108, 117, 0)
    val result = cstring2Binary("olu", 5)

    result should equal (expected)
  }

  it should " also truncate extra characters if length is greater than 'max' arguement" in {
    val expected2: Array[Byte] = Array(111, 108, 0)
    val result2 = cstring2Binary("olu", 3)

    result2 should equal (expected2)
  }


  "string2Binary" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(111, 108, 117)
    val result = string2Binary("olu", 5)
    result should equal (expected)
  }

  it should " also truncate extra extra characters if length is greater than 'max' arguement" in {
    val expected2: Array[Byte] = Array(111, 108)
    val result2 = string2Binary("olu", 2)

    result2 should equal (expected2)
  }

  "date2AbsoluteDate" should "give an Array of Bytes" in {
    val cal = new java.util.GregorianCalendar();
    cal.set(2011, 0, 1, 15, 30, 3)
    val date = cal.getTime
    val expected = Array(49, 49, 48, 49, 48, 49, 49, 53, 51, 48, 48, 51, 48, 48, 49, 43) //
    // "1101011530030001+"
    val result = date2AbsoluteDate(date)
    result should equal (expected)
  }

  "date2RelativeDate" should "give an Array of Bytes" in {
    val expected = Array(48, 48, 48, 48, 48, 48, 48, 48, 49, 53, 48, 48, 48, 48, 48, 82) //
    // "15mins = 49, 53"
    val result = date2RelativeDate(mm = 15)
    result should equal (expected)

  }
}

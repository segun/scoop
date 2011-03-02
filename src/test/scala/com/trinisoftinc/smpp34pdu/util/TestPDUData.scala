/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.PDUData._

class TestPDUData extends FlatSpec with ShouldMatchers {


  "int2Bytes" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(0, 0, 2, 4)
    val result = int2Bytes(0x0204)
    val result2 = int2Bytes(516)
    result should equal (expected)
    result2 should equal (expected)
  }

  "short2Bytes" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(4, 34)
    val result = short2Bytes(0x0422)
    val result2 = short2Bytes(1058)
    result should equal (expected)
    result2 should equal (expected)
  }

  "bytes2Int" should "give an Integer" in {
    val expected = 1058
    val result = bytes2Int(Array(0, 0, 4, 34))
    result should equal (expected)
  }

  "bytes2Short" should " give a Short" in {
    val expected: Short = 516
    val result = bytes2Short(Array(2, 4))
    result should equal (expected)
  }

  "bytes2String" should "give a String" in {
    val result = bytes2String(Array(97,98,99))
    val expected = "abc"

    result should equal (expected)
  }

  it should "give an empty String" in {
    val result = bytes2String(Array(0))
    val expected = ""
    result.length should equal (0)
    //expected.length should equal (0)
  }

  "sshort2bytes" should "give an Array of Bytes" in {
    val expected = Array(2)
    val result = sshort2Bytes(0x2)
    result should equal (expected)
  }

  "cstring2Bytes" should "guve an Array of Bytes, padded with 0" in {
    val expected: Array[Byte] = Array(111, 108, 117, 0)
    val result = cstring2Bytes("olu", 5)

    result should equal (expected)
  }

  it should " also truncate extra characters if length is greater than 'max' arguement" in {
    val expected2: Array[Byte] = Array(111, 108, 0)
    val result2 = cstring2Bytes("olu", 3)

    result2 should equal (expected2)
  }

  "string2Bytes" should "give an Array of Bytes" in {
    val expected: Array[Byte] = Array(111, 108, 117)
    val result = string2Bytes("olu", 5)
    result should equal (expected)
  }

  it should " also truncate extra extra characters if length is greater than 'max' arguement" in {
    val expected2: Array[Byte] = Array(111, 108)
    val result2 = string2Bytes("olu", 2)

    result2 should equal (expected2)
  }
}

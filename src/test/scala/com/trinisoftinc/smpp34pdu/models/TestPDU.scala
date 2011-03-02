package com.trinisoftinc.smpp34pdu.models

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util._

class TestPDU extends FlatSpec with ShouldMatchers {

  "A PDU" should "equals an Array of head ++ body after packing" in {
    val head: Array[Byte] = Array(
      0, 0, 0, 35,
      0, 0, 0, 2,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body: Array[Byte] = Array(
      67, 77, 84, 0,
      112, 97, 118, 101, 108, 0,
      99, 111, 122, 121, 0,
      52,
      2,
      1,
      0
    )
    val expected = head ++ body
    val result = PDU(Commands.BindTransmitter, 0x00, 0x01, body).pack
    result should equal(expected)
  }
}

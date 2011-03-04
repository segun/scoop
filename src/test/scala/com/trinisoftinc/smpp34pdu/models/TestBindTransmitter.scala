package com.trinisoftinc.smpp34pdu.models

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._


class TestBindTransmitter extends FlatSpec with ShouldMatchers {
  val head: Array[Byte] = Array(
    0, 0, 0, 36,
    0, 0, 0, 2,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val body2: Array[Byte] = Array(
    97, 98, 99, 100, 101, 102, 103, 0,
    120, 121, 122, 0,
    67, 77, 84, 0,
    52,
    2,
    1,
    0
  )

  "A BindTransmitter PDU" should "equals an Array of head ++ body after packing" in {
    val bind = BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "")

    val expected = head ++ body2

    val result = PDU(BIND_TRANSMITTER, ESME_ROK, 0x00000001, bind).pack

    result should equal(expected)
    bind.pack should equal (body2)
  }

  it should "equal a BindTransmitter PDU after packing and unpacking" in {
    val bind =
      BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "a")

    val expected:PDU = PDU(BIND_TRANSMITTER, ESME_ROK, 0x00000001, bind)

    val (result, pduPacker: BindRequest) = expected.unpack(expected.pack)

    result should equal (expected)
    pduPacker.pack should equal (bind.pack)
    pduPacker should equal (bind)
  }
}

package com.trinisoftinc.smpp34pdu.models

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._


class TestPDU extends FlatSpec with ShouldMatchers {
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

  "A PDU" should "equals an Array of head ++ body after packing" in {
    val bindTransmitter = BindTransmitter("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "")

    val expected = head ++ body2

    val result = PDU(BIND_TRANSMITTER, ESME_ROK, 0x00000001, bindTransmitter).pack

    result should equal(expected)
    bindTransmitter.pack should equal (body2)
  }

  it should "equal a PDU after packing and unpacking" in {
    val bindTransmitter:BindTransmitter =
      BindTransmitter("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "a")

    val expected:PDU = PDU(BIND_TRANSMITTER, ESME_ROK, 0x00000001, bindTransmitter)

    val (result, pduPacker: BindTransmitter) = expected.unpack(expected.pack)

    result.commandID should equal(expected.commandID)
    result.commandStatus should equal(expected.commandStatus)
    result.sequenceNumber should equal(expected.sequenceNumber)
    pduPacker.pack should equal (bindTransmitter.pack)
  }
}

package com.trinisoftinc.smpp34pdu.models

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._


class TestBinds extends FlatSpec with ShouldMatchers {
  val body: Array[Byte] = Array(
    97, 98, 99, 100, 101, 102, 103, 0,
    120, 121, 122, 0,
    67, 77, 84, 0,
    52,
    2,
    1,
    0
  )

  "A BindTransmitter PDU" should "equals an Array of head ++ body after packing" in {
    val head: Array[Byte] = Array(
      0, 0, 0, 36,
      0, 0, 0, 2,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val bind = BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "")

    val expected = head ++ body

    val result = PDU(BIND_TRANSMITTER, ESME_ROK, 0x00000001, bind).pack

    result should equal(expected)
    bind.pack should equal (body)
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

  "A BindReciever PDU" should "equal an Array of head ++ body after packing" in {
    val head: Array[Byte] = Array(
      0, 0, 0, 36,
      0, 0, 0, 1,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val bind = BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "")
    val expected = head ++ body
    val result = PDU(BIND_RECEIVER, ESME_ROK, 0x00000001, bind).pack

    result should equal (expected)
    bind.pack should equal (body)
  }

  it should "equal a BindReciever PDU after packing and unpacking" in {
    val bind = BindRequest("abcd", "yzx", "CMT", INTERFACE_VERSION, 2, 1, "b")
    val expected = PDU(BIND_RECEIVER, ESME_ROK, 0x00000001, bind)
    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal (expected)
    pduPacker.pack should equal (bind.pack)
    pduPacker should equal (bind)
  }

  "A BindTransceiver PDU" should "equal an Array of head ++ body after packing" in {
    val head: Array[Byte] = Array(
      0, 0, 0, 36,
      0, 0, 0, 9,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val bind = BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "")
    val expected = head ++ body
    val result = PDU(BIND_TRANSCEIVER, ESME_ROK, 0x00000001, bind).pack
    result should equal (expected)
    bind.pack should equal (body)
  }

  it should "equal a BindTransciever PDU after packing and unpacking" in {
    val bind = BindRequest("abcdefg", "xyz", "CMT", INTERFACE_VERSION, 2, 1, "d")
    val expected = PDU(BIND_TRANSCEIVER, ESME_ROK, 0x00000001, bind)
    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal (expected)
    pduPacker.pack should equal (bind.pack)
    pduPacker should equal (bind)
  }
}

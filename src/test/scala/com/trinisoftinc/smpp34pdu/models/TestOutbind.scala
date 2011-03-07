package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/7/11
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class TestOutbind extends FlatSpec with ShouldMatchers {
  val body = Array(
    97, 98, 99, 100, 0,
    120, 121, 122, 0
  )
  val head: Array[Byte] = Array(
    0, 0, 0, 25,
    0, 0, 0, 11,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  "An Outbind PDU" should "equal an array of head ++ body when packed" in {
    val expected = head ++ body
    val bind = Outbind("abcd", "xyz")
    val result = PDU(OUTBIND, ESME_ROK, 0x00000001, bind).pack

    result should equal (expected)
  }

  it should "equal an Outbind PDU when packed and unpacked" in {
    val bind = Outbind("abcd", "xyz")
    val expected = PDU(OUTBIND, ESME_ROK, 0x00000001, bind)
    val (result, pduPacker) = expected.unpack(expected.pack)

    expected should equal (result)
    expected.pack should equal (result.pack)
    pduPacker.pack should equal (body)
  }
}

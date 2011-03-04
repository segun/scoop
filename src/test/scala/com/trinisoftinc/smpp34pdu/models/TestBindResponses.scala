package com.trinisoftinc.smpp34pdu.models

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/3/11
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */

class TestBindResponses extends FlatSpec with ShouldMatchers {
  val head: Array[Int] = Array(
    0, 0, 0, 32,
    128, 0, 0, 2,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val head2: Array[Int] = Array(
    0, 0, 0, 27,
    128, 0, 0, 2,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val body: Array[Int] = Array(97,98,99,100,101,102,103,104,105,106,0,2,16,0,1,52)
  val body2: Array[Int] = Array(97,98,99,100,101,102,103,104,105,106,0)

  "A BindTransmitterResponse PDU(with TLV)" should "equal an Array of head ++ body (with TLV) when packed" in {
    val bindResponse = BindResponse("abcdefghij", TLV(scInterfaceVersion, INTERFACE_VERSION))
    val expected = head ++ body
    val result = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal (expected)
  }

  it should "equal an Array of head ++ body (without TLV) when packed" in {
    val bindResponse = BindResponse("abcdefghij")
    val expected = head2 ++ body2
    val result = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal (expected)
  }

  "A BindTransmitterResponse PDU(with TLV)" should "equal BindTransmitterResponse when packed and unpacked (with TLV)" in {
    val bindResponse = BindResponse("abcdefghij", TLV(scInterfaceVersion, INTERFACE_VERSION))
    val expected = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse)
    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal (expected)
    result.pack should equal (expected.pack)
    pduPacker should equal (bindResponse)
  }

  "A BindTransmitterResponse unpack method (without TLV)" should "return BindResponse (without TLV)" in {
    val expected = BindResponse("abcdefghij")
    val result = expected.unpack(body2)
    result should equal (expected)

  }
}
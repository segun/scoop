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

class TestBindTransmitterResponse extends FlatSpec with ShouldMatchers {
  val body: Array[Byte] = Array(97,98,99,100,101,102,103,104,105,106,0,2,16,0,1,52)
  val body2: Array[Byte] = Array(97,98,99,100,101,102,103,104,105,106,0)

  "pack (with TLV)" should "return an Array of Bytes (with TLV)" in {
    val result = BindTransmitterResponse("abcdefghij", TLV(scInterfaceVersion, INTERFACE_VERSION))
    result.pack should equal (body)
  }

  "pack (without TLV)" should "return an Array of Bytes (without TLV)" in {
    val result = BindTransmitterResponse("abcdefghij")
    result.pack should equal (body2)
  }

  "unpack (with TLV)" should "return BindTransmitterResponse (with TLV)" in {
    val expected = BindTransmitterResponse("abcdefghij", TLV(scInterfaceVersion, INTERFACE_VERSION))
    val result = expected.unpack(body)
    result should equal (expected)
  }

  "unpack (without TLV)" should "return BindTransmitterResponse (without TLV)" in {
    val expected = BindTransmitterResponse("abcdefghij")
    val result = expected.unpack(body2)
    result should equal (expected)

  }
}
package com.trinisoftinc.smpp34pdu.models

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/3/11
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */

class TestTLV extends FlatSpec with ShouldMatchers {
  "A scInterfaceVersion TLV" should "return an Array of Bytes" in {
    val expected = Array(2, 16, 0, 1, 52)
    val tlv = TLV(ScInterfaceVersion, INTERFACE_VERSION)
    val result = tlv.pack

    result should equal (expected)
  }

  it should "return a TLV if packed and unpacked" in {
    val expected = TLV(ScInterfaceVersion, INTERFACE_VERSION)
    val result = expected.unpack(expected.pack)
    val result2 = expected.unpack(Array(2, 16, 0, 1, 52))

    expected should equal (result)
    expected should equal (result2)
  }
}
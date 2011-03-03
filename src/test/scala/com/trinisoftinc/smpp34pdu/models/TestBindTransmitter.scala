package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/2/11
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.trinisoftinc.smpp34pdu.util._

class TestBindTransmitter extends FlatSpec with ShouldMatchers {
  val body2: Array[Byte] = Array(
    97, 98, 99, 100, 101, 102, 103, 0,
    120, 121, 122, 0,
    67, 77, 84, 0,
    52,
    2,
    1,
    0
  )

  "unpack" should "return BindTransmitter" in {
    val b = BindTransmitter("abcdefg", "xyz", "CMT", SMPPConstants.INTERFACE_VERSION, 2, 1, "")
    val result = b.unpack(body2)
    b.pack should  equal (result.pack)
  }

  it should "return an Array of Bytes when packed" in {
    val b = BindTransmitter("abcdefg", "xyz", "CMT", SMPPConstants.INTERFACE_VERSION, 2, 1, "")
    b.unpack(b.pack) should equal (b)
  }


  "pack" should "return an Array of Bytes" in {
    val b = BindTransmitter("abcdefg", "xyz", "CMT", SMPPConstants.INTERFACE_VERSION, 2, 1, "")

    b.pack should equal (body2)
  }
}
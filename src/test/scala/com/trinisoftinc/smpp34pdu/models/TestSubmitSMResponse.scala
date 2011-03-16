package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/11/11
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._


class TestSubmitSMResponse extends FlatSpec with ShouldMatchers {
  val head: List[Int] = List(
    0, 0, 0, 22,
    128, 0, 0, 4,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val body: List[Int] = List(97,98,99,100,101, 0)

  "A SMResponse" should "equal an List of head ++ body when packed" in {
    val ssmResponse = SMResponse("abcde")
    val expected = head ++ body
    val pdu = PDU(SUBMIT_SM_RESP, ESME_ROK, 0x00000001, ssmResponse)
    val result = pdu.pack
    result should equal  (expected)
    ssmResponse.pack should equal (body)
  }

  it should "equal SMResponse when packed and unpacked" in {
    val ssmResponse = SMResponse("abcde")
    val pdu = PDU(SUBMIT_SM_RESP, ESME_ROK, 0x00000001, ssmResponse)
    val (result, pduPacker) = pdu.unpack(pdu.pack)
    result should equal (pdu)
    pduPacker should equal (ssmResponse)
    pduPacker.pack should equal (ssmResponse.pack)
  }
}
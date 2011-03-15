package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/15/11
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class TestSubmitMultiResponse extends FlatSpec with ShouldMatchers {
  val head: List[Int] = List(
    0, 0, 0, 56,
    128, 0, 0, 33,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val body: List[Int] = List(
    97,98,99,100,101, 0,
    3,
    2, 1,
    101, 102, 103, 104, 0,
    0, 0, 0, 1,
    2, 1,
    105, 106, 107, 108, 0,
    0, 0, 0, 2,
    2, 1,
    109, 110, 111, 112, 0,
    0, 0, 0, 3
  )

  val um = List(
    UnsuccessfulAddress(2, 1, "efgh", ESME_RINVMSGLEN),
    UnsuccessfulAddress(2, 1, "ijkl", ESME_RINVCMDLEN),
    UnsuccessfulAddress(2, 1, "mnop", ESME_RINVCMDID)
  )

  "A SubmitMultiResponse PDU" should "equal a List of head ++ body" in {
    val submit_multi_resp = SubmitMultiResponse("abcde", 3, um)
    val pdu = PDU(SUBMIT_MULTI_RESP, ESME_ROK, 0x00000001, submit_multi_resp)
    val result = pdu.pack
    val expected = head ++ body
    result should equal (expected)
  }

  it should "equal a SubmitMultiResponse PDU when packed and unpacked" in {
    val submit_multi_resp = SubmitMultiResponse("abcde", 3, um)
    val pdu = PDU(SUBMIT_MULTI_RESP, ESME_ROK, 0x00000001, submit_multi_resp)
    val (result, pduPacker) = pdu.unpack(pdu.pack)

    result should equal (pdu)
    pduPacker should equal (submit_multi_resp)
    pduPacker.pack should equal (submit_multi_resp.pack)
  }
}
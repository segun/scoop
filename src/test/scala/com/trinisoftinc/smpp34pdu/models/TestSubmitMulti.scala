package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/15/11
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._


class TestSubmitMulti extends FlatSpec with ShouldMatchers {
  val body = List(
    67, 77, 84, 0,
    2, 1,
    97, 98, 99, 100, 0,
    2,
    1, 2, 1,
    101, 102, 103, 104, 0,
    2,
    97, 108, 105, 115, 116, 0,
    1, 2, 1,
    49, 49, 48, 49, 48, 49, 49, 53, 51, 48, 48, 51, 48, 48, 49, 43, 0,
    48, 48, 48, 48, 49, 52, 48, 48, 48, 48, 48, 48, 48, 48, 48, 82, 0,
    1, 1, 1, 1, 11,
    104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100
  )

  val head = List(
    0, 0, 0, 96,
    0, 0, 0, 33,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val sourceAddr = SMEAddress(2, 1, "abcd")
  val destAddr1 = SMEAddress(2, 1, "efgh")
  val destAddr2 = DistributionList("alist")
  val destAddresses = List(destAddr1, destAddr2)

  val cal = new java.util.GregorianCalendar();
  cal.set(2011, 0, 1, 15, 30, 3)
  val date = cal.getTime

  val sdt = binary2String(date2AbsoluteDate(date))
  val vp = binary2String(date2RelativeDate(dd = 14))

  "A SubmitMulti PDU" should "return a List of head ++ body when packed " in {
    val submit_multi = SubmitMulti("CMT", sourceAddr, destAddresses, 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(SUBMIT_MULTI, ESME_ROK, 0x00000001, submit_multi)
    var result = pdu.pack
    var expected = head ++ body
    result should equal (expected)
  }

  it should "equal a Submit_Multi PDU after packing and unpacking" in {
    val submit_multi = SubmitMulti("CMT", sourceAddr, destAddresses, 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(SUBMIT_MULTI, ESME_ROK, 0x00000001, submit_multi)
    val (result, pduPacker) = pdu.unpack(pdu.pack)
    result should equal (pdu)
    result.commandID should equal (pdu.commandID)
    result.commandStatus should equal (pdu.commandStatus)
    result.sequenceNumber should equal (pdu.sequenceNumber)
  }
}
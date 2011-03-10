package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/10/11
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.models._
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class TestSubmitSM extends FlatSpec with ShouldMatchers {
  val body = Array(
    67, 77, 84, 0,
    2, 1,
    97, 98, 99, 100, 0,
    2, 1,
    101, 102, 103, 104, 0,
    1, 2, 1,
    49, 49, 48, 49, 48, 49, 49, 53, 51, 48, 48, 51, 48, 48, 49, 43, 0,
    48, 48, 48, 48, 49, 52, 48, 48, 48, 48, 48, 48, 48, 48, 48, 82, 0,
    1, 1, 1, 1, 11,
    104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100
  )

  val head = Array(
    0, 0, 0, 87,
    0, 0, 0, 4,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  "A SubmitSM PDU" should "equal an Array of head ++ body" in {
    val cal = new java.util.GregorianCalendar();
    cal.set(2011, 0, 1, 15, 30, 3)
    val date = cal.getTime

    val sdt = binary2String(date2AbsoluteDate(date))
    val vp = binary2String(date2RelativeDate(dd = 14))
    val submit_sm = SubmitSM("CMT", 2, 1, "abcd", 2, 1, "efgh", 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(SUBMIT_SM, ESME_ROK, 0x00000001, submit_sm)
    val result = pdu.pack
    val expected = head ++ body
    result should equal (expected)
  }

  it should "equal a SubmitSM PDU after packing and unpacking" in {
    val cal = new java.util.GregorianCalendar();
    cal.set(2011, 0, 1, 15, 30, 3)
    val date = cal.getTime

    val sdt = binary2String(date2AbsoluteDate(date))
    val vp = binary2String(date2RelativeDate(dd = 14))
    val submit_sm = SubmitSM("CMT", 2, 1, "abcd", 2, 1, "efgh", 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(SUBMIT_SM, ESME_ROK, 0x00000001, submit_sm)
    val (result, pduPacker) = pdu.unpack(pdu.pack)
    pduPacker.asInstanceOf[SubmitSM].serviceType should equal (submit_sm.serviceType)
    pduPacker.asInstanceOf[SubmitSM].sourceAddr should equal (submit_sm.sourceAddr)
    pduPacker.asInstanceOf[SubmitSM].destinationAddr should equal (submit_sm.destinationAddr)
    pduPacker.asInstanceOf[SubmitSM].shortMessage should equal (submit_sm.shortMessage)
    pduPacker.asInstanceOf[SubmitSM].smLength should equal (submit_sm.smLength)
    pduPacker.asInstanceOf[SubmitSM].validityPeriod should equal (submit_sm.validityPeriod)
    pduPacker.asInstanceOf[SubmitSM].scheduleDeliveryTime should equal (submit_sm.scheduleDeliveryTime)

    result.commandID should equal (pdu.commandID)
    result.commandStatus should equal (pdu.commandStatus)
    result.sequenceNumber should equal (pdu.sequenceNumber)
  }
}
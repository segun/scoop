package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/16/11
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class TestDeliverSM extends FlatSpec with ShouldMatchers {
  val body = List(
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

  val head = List(
    0, 0, 0, 87,
    0, 0, 0, 5,
    0, 0, 0, 0,
    0, 0, 0, 1
  )

  val sourceAddr = SMEAddress(2, 1, "abcd")
  val destAddr = SMEAddress(2, 1, "efgh")

  val cal = new java.util.GregorianCalendar();
  cal.set(2011, 0, 1, 15, 30, 3)
  val date = cal.getTime

  val sdt = binary2String(date2AbsoluteDate(date))
  val vp = binary2String(date2RelativeDate(dd = 14))

  "A DeliverSM PDU" should "equal an List of head ++ body" in {
    val deliver_sm = DeliverSM("CMT", sourceAddr, destAddr, 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(DELIVER_SM, ESME_ROK, 0x00000001, deliver_sm)
    val result = pdu.pack
    val expected = head ++ body
    result should equal (expected)
  }

  it should "equal a DeliverSM PDU after packing and unpacking" in {
    val deliver_sm = DeliverSM("CMT", sourceAddr, destAddr, 1, 2, 1, sdt, vp, 1, 1, 1, 1, 11, "hello world")
    val pdu = PDU(DELIVER_SM, ESME_ROK, 0x00000001, deliver_sm)
    val (result, pduPacker) = pdu.unpack(pdu.pack)

    result should equal (pdu)
    pduPacker should equal (deliver_sm)
    pduPacker.pack should equal (deliver_sm.pack)
  }

}
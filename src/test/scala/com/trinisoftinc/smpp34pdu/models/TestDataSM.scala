package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/17/11
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class TestDataSM extends FlatSpec with ShouldMatchers {
  val body = List(
    67, 77, 84, 0,
    2, 1,
    97, 98, 99, 100, 0,
    2, 1,
    101, 102, 103, 104, 0,
    1, 1, 1
  )

  val head = List(
    0, 0, 0, 37,
    0, 0, 1, 3,
    0, 0, 0, 0,
    0, 0, 0, 1
  )
  val sourceAddr = SMEAddress(2, 1, "abcd")
  val destAddr = SMEAddress(2, 1, "efgh")

  "A DataSM PDU" should "return a List of head ++ body" in {
    val  data_sm = DataSM("CMT", sourceAddr, destAddr, 1, 1, 1)
    val  pdu = PDU(DATA_SM, ESME_ROK, 0x00000001, data_sm)
    val result = pdu.pack
    val expected = head ++ body
    result should equal (expected)
  }

  it should "equal a DataSM PDU when packed and unpacked" in {
    val data_sm = DataSM("CMT", sourceAddr, destAddr, 1, 1, 1)
    val pdu = PDU(DATA_SM, ESME_ROK, 0x00000001, data_sm)
    val (result, pduPacker) = pdu.unpack(pdu.pack)

    result should equal (pdu)
    pduPacker should equal (data_sm)
    pduPacker.pack should equal (data_sm.pack)
  }
}
package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/11/11
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

case class SubmitSMResponse(messageId: String) extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(messageId, 65)
  }

  def unpack(data: List[Int]): PDUPacker = {
    SubmitSMResponse(binary2String(data.takeWhile(_ != 0)))
  }
}
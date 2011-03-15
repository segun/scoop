/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

case class BindResponse(systemID: String = "", scInterfaceVersion: TLV = TLV()) extends PDUPacker {
  def pack(): List[Int] = {
    if (scInterfaceVersion.tag == ZERO) {
      cstring2Binary(systemID, 16)
    } else {
      cstring2Binary(systemID, 16) ++ scInterfaceVersion.pack
    }
  }

  def unpack(data: List[Int]): BindResponse = {
    val (sid: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    if (data2.length > 0) {
      val siv: TLV = new TLV().unpack(data2)
      BindResponse(sid, siv)
    } else {
      BindResponse(sid)
    }
  }
}

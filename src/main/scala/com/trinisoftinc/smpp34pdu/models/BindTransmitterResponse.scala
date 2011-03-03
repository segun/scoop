/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

case class BindTransmitterResponse(systemID: String = "", scInterfaceVersion: TLV = TLV()) extends PDUPacker {
  def pack(): Array[Byte] = {
    if (scInterfaceVersion.tag == ZERO) {
      cstring2Bytes(systemID, 16)
    } else {
      cstring2Bytes(systemID, 16) ++ scInterfaceVersion.pack
    }
  }

  def unpack(data: Array[Byte]): BindTransmitterResponse = {
    val (sid: String, data2: Array[Byte]) = (bytes2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    if (data2.length > 0) {
      val siv: TLV = new TLV().unpack(data2)
      BindTransmitterResponse(sid, siv)
    } else {
      BindTransmitterResponse(sid)
    }
  }
}

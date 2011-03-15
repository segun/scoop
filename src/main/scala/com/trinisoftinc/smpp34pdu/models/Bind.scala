package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/4/11
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

case class BindRequest(systemId: String = "",
                                password: String = "",
                                systemType: String = "",
                                interfaceVersion: Short = INTERFACE_VERSION,
                                addressTon: Short = 0x0001,
                                addressNpi: Short = 0x0001,
                                addressRange: String = "") extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(systemId, 16) ++
      cstring2Binary(password, 9) ++
      cstring2Binary(systemType, 13) ++
      sshort2Binary(interfaceVersion) ++
      sshort2Binary(addressTon) ++
      sshort2Binary(addressNpi) ++
      cstring2Binary(addressRange, 41)
  }

  def unpack(data: List[Int]) = {
    val (sid1: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (pwd1: String, data3: List[Int]) = (binary2String(data2.takeWhile(_ != 0)), data2.dropWhile(_ != 0).tail)
    val (sst1: String, data4: List[Int]) = (binary2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (itv1: Short, data5: List[Int]) = (data4.head.asInstanceOf[Short], data4.tail)
    val (adt1: Short, data6: List[Int]) = (data5.head.asInstanceOf[Short], data5.tail)
    val (adn1: Short, data7: List[Int]) = (data6.head.asInstanceOf[Short], data6.tail)
    val adr1: String = binary2String(data7.takeWhile(_ != 0))
    BindRequest(sid1, pwd1, sst1, itv1, adt1, adn1, adr1)
  }
}

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

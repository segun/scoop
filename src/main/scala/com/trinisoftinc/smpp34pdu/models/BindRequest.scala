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

case class BindRequest(sid: String = "",
                                pwd: String = "",
                                sst: String = "",
                                itv: Short = INTERFACE_VERSION,
                                adt: Short = 0x0001,
                                adn: Short = 0x0001,
                                adr: String = "") extends PDUPacker {
  def pack(): Array[Byte] = {
    cstring2Bytes(sid, 16) ++
      cstring2Bytes(pwd, 9) ++
      cstring2Bytes(sst, 13) ++
      sshort2Bytes(itv) ++
      sshort2Bytes(adt) ++
      sshort2Bytes(adn) ++
      cstring2Bytes(adr, 41)
  }

  def unpack(data: Array[Byte]) = {
    val (sid1: String, data2: Array[Byte]) = (bytes2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (pwd1: String, data3: Array[Byte]) = (bytes2String(data2.takeWhile(_ != 0)), data2.dropWhile(_ != 0).tail)
    val (sst1: String, data4: Array[Byte]) = (bytes2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (itv1: Short, data5: Array[Byte]) = (data4.head.asInstanceOf[Short], data4.tail)
    val (adt1: Short, data6: Array[Byte]) = (data5.head.asInstanceOf[Short], data5.tail)
    val (adn1: Short, data7: Array[Byte]) = (data6.head.asInstanceOf[Short], data6.tail)
    val adr1: String = bytes2String(data7.takeWhile(_ != 0))
    BindRequest(sid1, pwd1, sst1, itv1, adt1, adn1, adr1)
  }
}
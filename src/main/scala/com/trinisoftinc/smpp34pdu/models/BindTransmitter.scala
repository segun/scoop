package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/2/11
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
  import com.trinisoftinc.smpp34pdu.util._

case class BindTransmitter(systemID: String, password: String,systemType: String,
                           interfaceVersion: Short, addressTon: Short, addressNpi: Short,
                           addressRange: String) extends PDUPacker {
  def this() = {
    this("","","", SMPPConstants.INTERFACE_VERSION, 1, 1, "")
  }

  def pack(): Array[Byte] = {
      cstring2Bytes(systemID, 16) ++
      cstring2Bytes(password, 9) ++
      cstring2Bytes(systemType, 13) ++
      sshort2Bytes(interfaceVersion) ++
      sshort2Bytes(addressTon) ++
      sshort2Bytes(addressNpi) ++
      cstring2Bytes(addressRange, 41)
  }

  def unpack(data: Array[Byte]) = {
    val (sid: String, data2: Array[Byte]) = (bytes2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (pwd: String, data3: Array[Byte]) = (bytes2String(data2.takeWhile(_ != 0)), data2.dropWhile(_ != 0).tail)
    val (sst: String, data4: Array[Byte]) = (bytes2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (itv: Short, data5: Array[Byte]) = (data4.head.asInstanceOf[Short], data4.tail)
    val (adt: Short, data6: Array[Byte]) = (data5.head.asInstanceOf[Short], data5.tail)
    val (adn: Short, data7: Array[Byte]) = (data6.head.asInstanceOf[Short], data6.tail)
    println("D7: " + data7.takeWhile(_ != 0).mkString(","))
    val adr: String = bytes2String(data7.takeWhile(_ != 0))
    BindTransmitter(sid, pwd, sst, itv, adt, adn, adr)
  }
}
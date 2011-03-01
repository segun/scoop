package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/2/11
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */

case class BindTransmitter(systemID: String, password: String,systemType: String,
                           interfaceVersion: Short, addressTon: Short, addressNpi: Short,
                           addressRange: String) extends PDUPacker {

  def pack(): Array[Byte] = {
      cstring2Bytes(systemID, 16) ++
      cstring2Bytes(password, 9) ++
      cstring2Bytes(systemType, 13) ++
      sshort2Bytes(interfaceVersion) ++
      sshort2Bytes(addressTon) ++
      sshort2Bytes(addressNpi) ++
      cstring2Bytes(addressRange, 41)
  }
}
package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/7/11
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
case class Outbind(systemId: String = "", password: String = "") extends PDUPacker {
  def pack(): Array[Int] = {
    cstring2Binary(systemId, 16) ++ cstring2Binary (password, 9)
  }

  def unpack(data: Array[Int]) = {
    val (sid1: String, data2: Array[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val pwd1: String = binary2String(data2.takeWhile(_ != 0))
    Outbind(systemId, password)
  }
}
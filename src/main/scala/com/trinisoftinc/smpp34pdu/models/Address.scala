package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/14/11
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
case class Address(ton: Short = 0,
              npi: Short = 0,
              address: String = "") {

  def getBytes(): Array[Int] = {
      sshort2Binary(ton) ++ sshort2Binary(npi) ++ cstring2Binary(address, 21)
  }

  /**
   * returns the Address Object and the length of the whole address object
   */
  def fromBytes(data: Array[Int]): Tuple2[Address, Int] = {
    val (ton1, npi1, data1: Array[Int]) = (binary2SShort(data.head), binary2SShort(data.tail.head), data.tail.tail)
    val (addr1: String, data2: Array[Int]) = (binary2String(data1.takeWhile(_ != 0)), data1.dropWhile(_ != 0).tail)
    (Address(ton1, npi1), data.length - data2.length)
  }
}
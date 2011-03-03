/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._
import com.trinisoftinc.smpp34pdu.util.PDUData._

case class TLV(tag: Int, value: Any) {
  def pack(): Array[Byte] = {
    tag match {
      case n: Int if(n == scInterfaceVersion) => pack(1)
    }
  }

  def unpack(data: Array[Byte]): TLV = {
    var tag = data.slice(0, 2)
    var len = bytes2Short(data.slice(2, 4))
    var value = data.splitAt(4)._2
    if(len != value.length) {
      throw new IndexOutOfBoundsException("value is not of the correct length")
    }
    TLV(bytes2Short(tag), bytes2SShort(value))
  }

  private def pack(len: Int): Array[Byte] = {
    tag match {
      case n: Int if(n == scInterfaceVersion) =>
        short2Bytes(tag.asInstanceOf[Short]) ++ short2Bytes(1) ++ sshort2Bytes(INTERFACE_VERSION)
    }
  }
}

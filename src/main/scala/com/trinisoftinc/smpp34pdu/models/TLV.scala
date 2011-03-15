/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._
import com.trinisoftinc.smpp34pdu.util.PDUData._

case class TLV(tag: Short = ZERO.asInstanceOf[Short], value: Any = ZERO) {

  def pack(): List[Int] = {
    tag match {
      case ScInterfaceVersion => pack(1)
    }
  }

  private def pack(len: Short): List[Int] = {
    tag match {
      case ScInterfaceVersion =>
        short2Binary(tag.asInstanceOf[Short]) ++ short2Binary(len) ++ sshort2Binary(INTERFACE_VERSION)
    }
  }

  def unpack(data: List[Int]): TLV = {
    var tag = data.slice(0, 2)
    var len = binary2Short(data.slice(2, 4))
    var value = data.splitAt(4)._2
    if(len != value.length) {
      throw new IndexOutOfBoundsException("value is not of the correct length")
    }
    binary2Short(tag) match {
      case ScInterfaceVersion => TLV(binary2Short(tag), binary2SShort(value))
    }
  }
}

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
      case n: Short if n == ScInterfaceVersion => short2Binary(tag) ++ short2Binary(1) ++ sshort2Binary(INTERFACE_VERSION)
      case n: Short if n == UserMessageReference => short2Binary(tag) ++ short2Binary(2) ++ short2Binary(value.asInstanceOf[Short])
      case _: Short => List.empty
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
      case n: Short if n == ScInterfaceVersion => TLV(binary2Short(tag), binary2SShort(value))
    }
  }
}

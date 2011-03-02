/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util._

trait PDUPacker
trait PDUUnPacker

case class PDU(commandID: Int, commandStatus: Int, 
               sequenceNumber: Int, body: Array[Byte]) extends PDUPacker {
  def pack(): Array[Byte] = {
    val len = body.length + SMPPConstants.HeaderOctectsSize
    int2Bytes(len) ++
    int2Bytes(commandID) ++
    int2Bytes(commandStatus) ++
    int2Bytes(sequenceNumber) ++
    body
  }
}






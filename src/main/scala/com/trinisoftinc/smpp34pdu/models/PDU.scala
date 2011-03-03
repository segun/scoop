/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util._

trait PDUPacker {
  def pack(): Array[Byte]
  def unpack(data: Array[Byte]): PDUPacker
}

trait PDUUnPacker

case class PDU(commandID: Int, commandStatus: Int, 
               sequenceNumber: Int, body: PDUPacker) {

  def this() = {
    this(0,0,0,null)
  }

  def pack(): Array[Byte] = {
    val len = body.pack.length + SMPPConstants.HEADEROCTETSIZE
    int2Bytes(len) ++
    int2Bytes(commandID) ++
    int2Bytes(commandStatus) ++
    int2Bytes(sequenceNumber) ++
    body.pack
  }

  def unpack(data: Array[Byte]): Tuple2[PDU, PDUPacker] = {
    val header = data.slice(0, 16)
    var splitted = for {
      x <- 0 to header.length by 4
    } yield (header.slice(x, x + 4))



    val len = splitted(0)
    val commandId = bytes2Int(splitted(1))
    val commandStatus = bytes2Int(splitted(2))
    val sequenceNumber = bytes2Int(splitted(3))

    val bodyAsBytes = data.slice(16, data.length)
    val pduPacker = body.unpack(bodyAsBytes)
    (PDU(commandId, commandStatus, sequenceNumber, pduPacker), pduPacker)
  }
}





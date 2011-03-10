/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util._

trait PDUPacker {
  def pack(): Array[Int]
  def unpack(data: Array[Int]): PDUPacker
}

case class PDU(commandID: Int, commandStatus: Int, 
               sequenceNumber: Int, body: PDUPacker) {

  def this() = {
    this(0,0,0,null)
  }

  def pack(): Array[Int] = {
    val len = body.pack.length + SMPPConstants.HEADEROCTETSIZE
    int2Binary(len) ++
    int2Binary(commandID) ++
    int2Binary(commandStatus) ++
    int2Binary(sequenceNumber) ++
    body.pack
  }

  def unpack(data: Array[Int]): Tuple2[PDU, PDUPacker] = {
    val header = data.slice(0, 16)
    var splitted = for {
      x <- 0 to header.length by 4
    } yield (header.slice(x, x + 4))

    val len = splitted(0)
    val commandId = binary2Int(splitted(1))
    val commandStatus = binary2Int(splitted(2))
    val sequenceNumber = binary2Int(splitted(3))

    val bodyAsBytes = data.slice(16, data.length)
    val pduPacker: PDUPacker = body.unpack(bodyAsBytes)
    (PDU(commandId, commandStatus, sequenceNumber, pduPacker), pduPacker)
  }
}





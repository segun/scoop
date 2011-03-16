package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/9/11
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._
import com.trinisoftinc.smpp34pdu.util.PDUData._

class SM(serviceType: String = "",
         sourceAddress: Address = SMEAddress(),
         destAddress: Address = SMEAddress(),
         esmClass: Short = 0,
         registeredDelivery: Short = 0,
         dataCoding: Short = 0) extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(serviceType, 6) ++
    sourceAddress.getBytes ++
    destAddress.getBytes ++
    sshort2Binary(esmClass) ++
    sshort2Binary(registeredDelivery) ++
    sshort2Binary (dataCoding)
  }

  def unpack(data: List[Int]): PDUPacker = this
}

class SubmitDeliverSM(serviceType: String = "",
                      sourceAddress: Address = SMEAddress(),
                      destAddress: Address = SMEAddress(),
                      esmClass: Short = 0,
                      protocolId: Short = 0,
                      priorityFlag: Short = 0,
                      scheduleDeliveryTime: String = "",
                      validityPeriod: String = "",
                      registeredDelivery: Short = 0,
                      replaceIfPresent: Short = 0,
                      dataCoding: Short = 0,
                      smDefaultMsgId: Short = 0,
                      smLength: Short = 0,
                      shortMessage: String = "",
                      tlv: List[TLV] = List.empty) extends SM with Submit {

  override def pack(): List[Int] = {
    cstring2Binary(serviceType, 6) ++
      sourceAddress.getBytes ++
      destAddress.getBytes ++
      sshort2Binary(esmClass) ++
      sshort2Binary(protocolId) ++
      sshort2Binary(priorityFlag) ++
      cstring2Binary(scheduleDeliveryTime, 1, 17) ++
      cstring2Binary(validityPeriod, 1, 17) ++
      sshort2Binary(registeredDelivery) ++
      sshort2Binary(replaceIfPresent) ++
      sshort2Binary(dataCoding) ++
      sshort2Binary(smDefaultMsgId) ++
      sshort2Binary(smLength) ++
      string2Binary(shortMessage, 254)
  }

  override def unpack(data: List[Int]): PDUPacker = this

}

case class SubmitSM(serviceType: String = "",
                    sourceAddress: Address = SMEAddress(),
                    destAddress: Address = SMEAddress(),
                    esmClass: Short = 0,
                    protocolId: Short = 0,
                    priorityFlag: Short = 0,
                    scheduleDeliveryTime: String = "",
                    validityPeriod: String = "",
                    registeredDelivery: Short = 0,
                    replaceIfPresent: Short = 0,
                    dataCoding: Short = 0,
                    smDefaultMsgId: Short = 0,
                    smLength: Short = 0,
                    shortMessage: String = "",
                    tlv: List[TLV] = List.empty)
  extends SubmitDeliverSM(
    serviceType,
    sourceAddress,
    destAddress,
    esmClass,
    protocolId,
    priorityFlag,
    scheduleDeliveryTime,
    validityPeriod,
    registeredDelivery,
    replaceIfPresent,
    dataCoding,
    smDefaultMsgId,
    smLength,
    shortMessage,
    tlv
  ) {

  override def pack(): List[Int] = {
    val body: List[Int] = super.pack

    if (!tlv.isEmpty) {
      //tlv.foldLeft(TLV().pack)((a, b) => a ++ b.pack)
      val tlvs = (TLV().pack /: tlv)(_ ++ _.pack)
      body ++ tlvs
    } else {
      body
    }
  }

  override def unpack(data: List[Int]): PDUPacker = {
    val (serviceType1: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (sourceAddrTon1, sourceAddrNpi1, data3: List[Int]) = (binary2SShort(data2.head), binary2SShort(data2.tail.head), data2.tail.tail)
    val (sourceAddr1: String, data4: List[Int]) = (binary2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (destAddrTon1, destAddrNpi1, data5: List[Int]) = (binary2SShort(data4.head), binary2SShort(data4.tail.head), data4.tail.tail)
    val (destinationAddr1: String, data6: List[Int]) = (binary2String(data5.takeWhile(_ != 0)), data5.dropWhile(_ != 0).tail)
    val (esmClass1, protocolId1, data7: List[Int]) = (binary2SShort(data6.head), binary2SShort(data6.tail.head), data6.tail.tail)
    val (priorityFlag1, data71: List[Int]) = (binary2SShort(data7.head), data7.tail)
    val (scheduleDeliveryTime1: String, data8: List[Int]) = (binary2String(data71.takeWhile(_ != 0)), data71.dropWhile(_ != 0).tail)
    val (validityPeriod1: String, data9: List[Int]) = (binary2String(data8.takeWhile(_ != 0)), data8.dropWhile(_ != 0).tail)
    val (registeredDelivery1, replaceIfPresent1, data10: List[Int]) = (binary2SShort(data9.head), binary2SShort(data9.tail.head), data9.tail.tail)
    val (dataCoding1, smDefaultMsgId1, data11: List[Int]) = (binary2SShort(data10.head), binary2SShort(data10.tail.head), data10.tail.tail)
    val (smLength1, data12: List[Int]) = (binary2SShort(data11.head), data11.tail)
    val (shortMessage1, tlvs: List[Int]) = (binary2String(data12.take(smLength1)), data12.drop(smLength1))

    val tlv1: List[TLV] = getTLV(tlvs)

    val sourceAddress1 = SMEAddress(sourceAddrTon1, sourceAddrNpi1, sourceAddr1)
    val destAddress1 = SMEAddress(destAddrTon1, destAddrNpi1, destinationAddr1)

    SubmitSM(serviceType1,
      sourceAddress1,
      destAddress1,
      esmClass1,
      protocolId1,
      priorityFlag1,
      scheduleDeliveryTime1,
      validityPeriod1,
      registeredDelivery1,
      replaceIfPresent1,
      dataCoding1,
      smDefaultMsgId1,
      smLength1,
      shortMessage1,
      tlv1
    )
  }
}

case class SubmitSMResponse(messageId: String) extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(messageId, 65)
  }

  def unpack(data: List[Int]): PDUPacker = {
    SubmitSMResponse(binary2String(data.takeWhile(_ != 0)))
  }
}
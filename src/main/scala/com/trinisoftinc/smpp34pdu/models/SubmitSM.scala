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

case class SubmitSM(serviceType: String = "",
                    sourceAddrTon: Short = 0,
                    sourceAddrNpi: Short = 0,
                    sourceAddr: String = "",
                    destAddrTon: Short = 0,
                    destAddrNpi: Short = 0,
                    destinationAddr: String = "",
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
                    tlv: Array[TLV] = Array.empty)
  extends PDUPacker {

  def takeTLV(tlvTag: Short, data: Array[Int]): Tuple2[TLV, Array[Int]] = {
    tlvTag match {
      case UserMessageReference => (TLV().unpack(data.take(6)), data.drop(6))
      case SourcePort => (TLV().unpack(data.take(6)), data.drop(6))
      case SourceAddressSubunit => (TLV().unpack(data.take(5)), data.drop(5))
      case DestinationPort => (TLV().unpack(data.take(6)), data.drop(6))
      case DestinationAddressSubunit => (TLV().unpack(data.take(5)), data.drop(5))
      case SarMsgRefNum => (TLV().unpack(data.take(6)), data.drop(6))
      case SarTotalSegments => (TLV().unpack(data.take(5)), data.drop(5))
      case SarSegmentSeqnum => (TLV().unpack(data.take(5)), data.drop(5))
      case MoreMessagesToSend => (TLV().unpack(data.take(5)), data.drop(5))
      case PayLoadType => (TLV().unpack(data.take(5)), data.drop(5))
      case MessagePayload => {
        //first 2 bytes is tag, next two is length
        val len = binary2Short(data.drop(2).take(2))
        val totalLength = len + 4;
        (TLV().unpack(data.take(totalLength)), data.drop(totalLength))
      }
      case PrivacyIndicator => (TLV().unpack(data.take(5)), data.drop(5))
      case CallBackNum => {
        //first 2 bytes is tag, next two is length
        val len = binary2Short(data.drop(2).take(2))
        val totalLength = len + 4;
        (TLV().unpack(data.take(totalLength)), data.drop(totalLength))
      }
      case CallBackNumPresInd => (TLV().unpack(data.take(5)), data.drop(5))
      case CallBackNumAtag => {
        //first 2 bytes is tag, next two is length
        val len = binary2Short(data.drop(2).take(2))
        val totalLength = len + 4;
        (TLV().unpack(data.take(totalLength)), data.drop(totalLength))
      }
      case SourceSubAddress => {
        //first 2 bytes is tag, next two is length
        val len = binary2Short(data.drop(2).take(2))
        val totalLength = len + 4;
        (TLV().unpack(data.take(totalLength)), data.drop(totalLength))
      }
      case DestinationSubAddress => {
        //first 2 bytes is tag, next two is length
        val len = binary2Short(data.drop(2).take(2))
        val totalLength = len + 4;
        (TLV().unpack(data.take(totalLength)), data.drop(totalLength))
      }
      case UserResponseCode => (TLV().unpack(data.take(5)), data.drop(5))
      case DisplayTime => (TLV().unpack(data.take(5)), data.drop(5))
      case SmsSignal => (TLV().unpack(data.take(6)), data.drop(6))
      case MsValidity => (TLV().unpack(data.take(5)), data.drop(5))
      case MsMsgWaitFacilities => (TLV().unpack(data.take(5)), data.drop(5))
      case NumberOfMessages => (TLV().unpack(data.take(5)), data.drop(5))
      case AlertOnMessageDelivery => (TLV().unpack(data.take(4)), data.drop(4))
      case LanguageIndicator => (TLV().unpack(data.take(5)), data.drop(5))
      case ItsReplyType => (TLV().unpack(data.take(5)), data.drop(5))
      case ItsSessionInfo => (TLV().unpack(data.take(6)), data.drop(6))
      case UssdServiceOp => (TLV().unpack(data.take(5)), data.drop(5))
    }
  }

  def pack(): Array[Int] = {
    val body: Array[Int] = cstring2Binary(serviceType, 6) ++
      sshort2Binary(sourceAddrTon) ++
      sshort2Binary(sourceAddrNpi) ++
      cstring2Binary(sourceAddr, 21) ++
      sshort2Binary(destAddrTon) ++
      sshort2Binary(destAddrNpi) ++
      cstring2Binary(destinationAddr, 21) ++
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

    if (!tlv.isEmpty) {
      //tlv.foldLeft(TLV().pack)((a, b) => a ++ b.pack)
      val tlvs = (TLV().pack /: tlv)(_ ++ _.pack)
      body ++ tlvs
    } else {
      body
    }
  }

  def unpack(data: Array[Int]): PDUPacker = {
    val (serviceType1: String, data2: Array[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (sourceAddrTon1, sourceAddrNpi1, data3: Array[Int]) = (binary2SShort(data2.head), binary2SShort(data2.tail.head), data2.tail.tail)
    val (sourceAddr1: String, data4: Array[Int]) = (binary2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (destAddrTon1, destAddrNpi1, data5: Array[Int]) = (binary2SShort(data4.head), binary2SShort(data4.tail.head), data4.tail.tail)
    val (destinationAddr1: String, data6: Array[Int]) = (binary2String(data5.takeWhile(_ != 0)), data5.dropWhile(_ != 0).tail)
    val (esmClass1, protocolId1, data7: Array[Int]) = (binary2SShort(data6.head), binary2SShort(data6.tail.head), data6.tail.tail)
    val (priorityFlag1, data71: Array[Int]) = (binary2SShort(data7.head), data7.tail)
    val (scheduleDeliveryTime1: String, data8: Array[Int]) = (binary2String(data71.takeWhile(_ != 0)), data71.dropWhile(_ != 0).tail)
    val (validityPeriod1: String, data9: Array[Int]) = (binary2String(data8.takeWhile(_ != 0)), data8.dropWhile(_ != 0).tail)
    val (registeredDelivery1, replaceIfPresent1, data10: Array[Int]) = (binary2SShort(data9.head), binary2SShort(data9.tail.head), data9.tail.tail)
    val (dataCoding1, smDefaultMsgId1, data11: Array[Int]) = (binary2SShort(data10.head), binary2SShort(data10.tail.head), data10.tail.tail)
    val (smLength1, data12: Array[Int]) = (binary2SShort(data11.head), data11.tail)
    val (shortMessage1, tlvs: Array[Int]) = (binary2String(data12.take(smLength1)), data12.drop(smLength1))
    val tlv1: Array[TLV] = getTLV(tlvs)
    return SubmitSM(serviceType1,
      sourceAddrTon1,
      sourceAddrNpi1,
      sourceAddr1,
      destAddrTon1,
      destAddrNpi1,
      destinationAddr1,
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

  private def getTLV(data: Array[Int]): Array[TLV] = {
    if (data.isEmpty) Array.empty
    else {
      val tag = data.take(2)
      val (tlv: TLV, rem: Array[Int]) = takeTLV(binary2Short(tag), data)
      Array(tlv) ++ getTLV(rem)
    }
  }
}
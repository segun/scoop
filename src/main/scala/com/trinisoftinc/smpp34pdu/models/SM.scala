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

class SM(_serviceType: String = "",
         _sourceAddress: Address = SMEAddress(),
         _destAddress: Address = SMEAddress(),
         _esmClass: Short = 0,
         _registeredDelivery: Short = 0,
         _dataCoding: Short = 0,
         _tlv: List[TLV] = List.empty) extends PDUPacker with Submit {

  def serviceType = _serviceType

  def sourceAddress = _sourceAddress

  def destAddress = _destAddress

  def esmClass = _esmClass

  def registeredDelivery = _registeredDelivery

  def dataCoding = _dataCoding

  def tlv = _tlv

  def pack(): List[Int] = {
    cstring2Binary(serviceType, 6) ++
      sourceAddress.getBytes ++
      destAddress.getBytes ++
      sshort2Binary(esmClass) ++
      sshort2Binary(registeredDelivery) ++
      sshort2Binary(dataCoding)
  }

  def unpack(data: List[Int]): PDUPacker = {
    val (serviceType1: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (sourceAddrTon1, sourceAddrNpi1, data3: List[Int]) = (binary2SShort(data2.head), binary2SShort(data2.tail.head), data2.tail.tail)
    val (sourceAddr1: String, data4: List[Int]) = (binary2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (destAddrTon1, destAddrNpi1, data5: List[Int]) = (binary2SShort(data4.head), binary2SShort(data4.tail.head), data4.tail.tail)
    val (destinationAddr1: String, data6: List[Int]) = (binary2String(data5.takeWhile(_ != 0)), data5.dropWhile(_ != 0).tail)
    val (esmClass1, registeredDelivery1, data7: List[Int]) = (binary2SShort(data6.head), binary2SShort(data6.tail.head), data6.tail.tail)
    val (dataCoding1, tlvs: List[Int]) = (binary2SShort(data7.head), data7.tail)

    val tlv1: List[TLV] = getTLV(tlvs)

    val sourceAddress1 = SMEAddress(sourceAddrTon1, sourceAddrNpi1, sourceAddr1)
    val destAddress1 = SMEAddress(destAddrTon1, destAddrNpi1, destinationAddr1)

    new SM(serviceType, sourceAddress1, destAddress1, esmClass1, registeredDelivery1, dataCoding1, tlv1)
  }
}

class SubmitDeliverSM(_serviceType: String = "",
                      _sourceAddress: Address = SMEAddress(),
                      _destAddress: Address = SMEAddress(),
                      _esmClass: Short = 0,
                      _protocolId: Short = 0,
                      _priorityFlag: Short = 0,
                      _scheduleDeliveryTime: String = "",
                      _validityPeriod: String = "",
                      _registeredDelivery: Short = 0,
                      _replaceIfPresent: Short = 0,
                      _dataCoding: Short = 0,
                      _smDefaultMsgId: Short = 0,
                      _smLength: Short = 0,
                      _shortMessage: String = "",
                      _tlv: List[TLV] = List.empty) extends SM {

  override def serviceType = _serviceType

  override def sourceAddress = _sourceAddress

  override def destAddress = _destAddress

  override def esmClass = _esmClass

  def protocolId = _protocolId

  def priorityFlag = _priorityFlag

  def scheduleDeliveryTime = _scheduleDeliveryTime

  def validityPeriod = _validityPeriod

  override def registeredDelivery = _registeredDelivery

  def replaceIfPresent = _replaceIfPresent

  override def dataCoding = _dataCoding

  def smDefaultMsgId = _smDefaultMsgId

  def smLength = _smLength

  def shortMessage = _shortMessage

  override def tlv = _tlv


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

    new SubmitDeliverSM(serviceType1,
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

case class SubmitSM(_serviceType: String = "",
                    _sourceAddress: Address = SMEAddress(),
                    _destAddress: Address = SMEAddress(),
                    _esmClass: Short = 0,
                    _protocolId: Short = 0,
                    _priorityFlag: Short = 0,
                    _scheduleDeliveryTime: String = "",
                    _validityPeriod: String = "",
                    _registeredDelivery: Short = 0,
                    _replaceIfPresent: Short = 0,
                    _dataCoding: Short = 0,
                    _smDefaultMsgId: Short = 0,
                    _smLength: Short = 0,
                    _shortMessage: String = "",
                    _tlv: List[TLV] = List.empty)
  extends SubmitDeliverSM(
    _serviceType,
    _sourceAddress,
    _destAddress,
    _esmClass,
    _protocolId,
    _priorityFlag,
    _scheduleDeliveryTime,
    _validityPeriod,
    _registeredDelivery,
    _replaceIfPresent,
    _dataCoding,
    _smDefaultMsgId,
    _smLength,
    _shortMessage,
    _tlv
  ) {

  override def pack(): List[Int] = {
    val allowedTLVs = List(
      UserMessageReference, SourcePort, SourceAddressSubunit, DestinationPort, DestinationAddressSubunit,
      SarMsgRefNum, SarTotalSegments, SarSegmentSeqnum, MoreMessagesToSend, PayLoadType, MessagePayload,
      PrivacyIndicator, CallBackNum, CallBackNumPresInd, CallBackNumAtag, SourceSubAddress, DestinationSubAddress,
      UserResponseCode, DisplayTime, SmsSignal, MsValidity, MsMsgWaitFacilities, NumberOfMessages,
      AlertOnMessageDelivery, LanguageIndicator, ItsReplyType, ItsSessionInfo, UssdServiceOp
    )

    val isAllowed = tlv.forall(x => allowedTLVs.contains(x.tag))

    if (!isAllowed) throw new MatchError("One of the TLVs supplied is not allowed. \nALLOWED: " +
      allowedTLVs + "\nSUPPLIED: " + tlv)

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
    val superUnpack = super.unpack(data).asInstanceOf[SubmitDeliverSM]
    SubmitSM(superUnpack.serviceType,
      superUnpack.sourceAddress,
      superUnpack.destAddress,
      superUnpack.esmClass,
      superUnpack.protocolId,
      superUnpack.priorityFlag,
      superUnpack.scheduleDeliveryTime,
      superUnpack.validityPeriod,
      superUnpack.registeredDelivery,
      superUnpack.replaceIfPresent,
      superUnpack.dataCoding,
      superUnpack.smDefaultMsgId,
      superUnpack.smLength,
      superUnpack.shortMessage,
      superUnpack.tlv)
  }
}

case class DeliverSM(_serviceType: String = "",
                     _sourceAddress: Address = SMEAddress(),
                     _destAddress: Address = SMEAddress(),
                     _esmClass: Short = 0,
                     _protocolId: Short = 0,
                     _priorityFlag: Short = 0,
                     _scheduleDeliveryTime: String = "",
                     _validityPeriod: String = "",
                     _registeredDelivery: Short = 0,
                     _replaceIfPresent: Short = 0,
                     _dataCoding: Short = 0,
                     _smDefaultMsgId: Short = 0,
                     _smLength: Short = 0,
                     _shortMessage: String = "",
                     _tlv: List[TLV] = List.empty)
  extends SubmitDeliverSM(
    _serviceType,
    _sourceAddress,
    _destAddress,
    _esmClass,
    _protocolId,
    _priorityFlag,
    _scheduleDeliveryTime,
    _validityPeriod,
    _registeredDelivery,
    _replaceIfPresent,
    _dataCoding,
    _smDefaultMsgId,
    _smLength,
    _shortMessage,
    _tlv
  ) {

  override def pack(): List[Int] = {

    val allowedTLVs = List(
      UserMessageReference, SourcePort, DestinationPort,
      SarMsgRefNum, SarTotalSegments, SarSegmentSeqnum, UserResponseCode, PrivacyIndicator, PayLoadType,
      MessagePayload, CallBackNum, SourceSubAddress, DestinationSubAddress, LanguageIndicator, ItsSessionInfo,
      NetworkErrorCode, MessageState, ReceiptedMessageId
    )

    val isAllowed = tlv.forall(x => allowedTLVs.contains(x.tag))

    if (!isAllowed) throw new MatchError("One of the TLVs supplied is not allowed. \nALLOWED: " +
      allowedTLVs + "\nSUPPLIED: " + tlv)

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
    val superUnpack = super.unpack(data).asInstanceOf[SubmitDeliverSM]
    DeliverSM(superUnpack.serviceType,
      superUnpack.sourceAddress,
      superUnpack.destAddress,
      superUnpack.esmClass,
      superUnpack.protocolId,
      superUnpack.priorityFlag,
      superUnpack.scheduleDeliveryTime,
      superUnpack.validityPeriod,
      superUnpack.registeredDelivery,
      superUnpack.replaceIfPresent,
      superUnpack.dataCoding,
      superUnpack.smDefaultMsgId,
      superUnpack.smLength,
      superUnpack.shortMessage,
      superUnpack.tlv)
  }
}

case class DataSM(_serviceType: String = "",
                  _sourceAddress: Address = SMEAddress(),
                  _destAddress: Address = SMEAddress(),
                  _esmClass: Short = 0,
                  _registeredDelivery: Short = 0,
                  _dataCoding: Short = 0,
                  _tlv: List[TLV] = List.empty) extends SM(_serviceType,
  _sourceAddress,
  _destAddress,
  _esmClass,
  _registeredDelivery,
  _dataCoding,
  _tlv) {

  override def pack(): List[Int] = {
    val allowedTLVs = List(
      SourcePort, SourceAddressSubunit, SourceNetworkType, SourceBearerType, SourceTelematicsId,
      DestinationPort, DestinationAddressSubunit, DestinationNetworkType, DestinationBearerType, DestinationTelematicsId,
      SarMsgRefNum, SarTotalSegments, SarSegmentSeqnum, MoreMessagesToSend, QosTimeToLive, PayLoadType, MessagePayload,
      SetDpf, ReceiptedMessageId, MessageState, NetworkErrorCode, UserMessageReference, PrivacyIndicator,
      CallBackNum, CallBackNumPresInd, CallBackNumAtag, SourceSubAddress, DestinationSubAddress, UserResponseCode,
      DisplayTime, SmsSignal, MsValidity, MsMsgWaitFacilities, NumberOfMessages, AlertOnMessageDelivery,
      LanguageIndicator, ItsReplyType, ItsSessionInfo
    )

    val isAllowed = tlv.forall(x => allowedTLVs.contains(x.tag))

    if (!isAllowed) throw new MatchError("One of the TLVs supplied is not allowed. \nALLOWED: " +
      allowedTLVs + "\nSUPPLIED: " + tlv)

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
    val superUnpack = super.unpack(data).asInstanceOf[SM]
    DataSM(superUnpack.serviceType,
      superUnpack.sourceAddress,
      superUnpack.destAddress,
      superUnpack.esmClass,
      superUnpack.registeredDelivery,
      superUnpack.dataCoding,
      superUnpack.tlv)
  }
}

case class SMResponse(messageId: String) extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(messageId, 65)
  }

  def unpack(data: List[Int]): PDUPacker = {
    SMResponse(binary2String(data.takeWhile(_ != 0)))
  }
}

case class DataSMResponse(messageId: String = "", tlv: List[TLV] = List.empty) extends PDUPacker with Submit {
  def pack(): List[Int] = {

    val allowedTLVs = List(
      DeliveryFailureReason, NetworkErrorCode, AdditionalStatusInfoText, DpfResult
    )
    val isAllowed = tlv.forall(x => allowedTLVs.contains(x.tag))
    if (!isAllowed) throw new MatchError("One of the TLVs supplied is not allowed. \nALLOWED: " +
      allowedTLVs + "\nSUPPLIED: " + tlv)
    val body = cstring2Binary(messageId, 65)
    if(!tlv.isEmpty) {
      body ++ (TLV().pack /: tlv) (_ ++ _.pack)
    } else {
      body
    }
  }

  def unpack(data: List[Int]): PDUPacker = {
    val (messageId1, tlvs: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val tlv1 = getTLV(tlvs)
    DataSMResponse(messageId1, tlv1)
  }
}
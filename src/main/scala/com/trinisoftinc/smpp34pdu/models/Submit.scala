package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/14/11
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._
import com.trinisoftinc.smpp34pdu.util.PDUData._
trait  Submit {

  protected def takeTLV(tlvTag: Short, data: List[Int]): Tuple2[TLV, List[Int]] = {
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

/*
  protected def getTLV(data: List[Int]): List[TLV] = {
    if (data.isEmpty) List.empty
    else {
      val tag = data.take(2)
      val (tlv: TLV, rem: List[Int]) = takeTLV(binary2Short(tag), data)
      List(tlv) ++ getTLV(rem)
    }
  }
*/

  protected def getTLV(data: List[Int], acc: List[TLV] = Nil): List[TLV] = {
    if(data.isEmpty) acc.reverse
    else {
      val tag = data.take(2)
      val (tlv: TLV, rem: List[Int]) = takeTLV(binary2Short(tag), data)
      val thisAcc = List(tlv) ++ acc
      getTLV(rem)
    }
  }
}
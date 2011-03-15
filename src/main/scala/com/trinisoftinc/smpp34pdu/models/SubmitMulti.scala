package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/14/11
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.SMPPConstants._
import com.trinisoftinc.smpp34pdu.util.PDUData._

object SubmitMulti {
  def destAddressesToBytes(destAddresses: List[Address]): List[Int] = {
    if (destAddresses.length <= MAX_DEST_ADDRESSES) {
      (new Address().getBytes /: destAddresses)((a, b) => a ++ (b.destFlag :: b.getBytes))
    } else {
      throw new MatchError("Destination Addresses should not exceed" + MAX_DEST_ADDRESSES + " but is " + destAddresses.length)
    }
  }

  def getAddresses(data: List[Int], count: Int, len: Int,
                           addresses: List[Address] = Nil,
                           positions: List[Int] = Nil): (List[Address], List[Int]) = {
    if (count == len) {
      (addresses.reverse, positions.reverse)
    } else {
      val (addr, rest) = data.span(_ != 0)
      val newdata = rest.tail
      val position = addr.length + 1
      val flag = addr.head
      val address = {
        if (flag == SMEAddressFlag) SMEAddress().fromBytes(addr)
        else DistributionList().fromBytes(addr)
      }
      getAddresses(newdata, count + 1, len, address :: addresses, position :: positions)
    }
  }

}

case class SubmitMulti(serviceType: String = "",
                       sourceAddress: Address = SMEAddress(),
                       destAddresses: List[Address] = List.empty,
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
                       tlv: List[TLV] = List.empty) extends PDUPacker with Submit {


  /*
    private def getAddresses(data: List[Int], count: Int, len: Int): Tuple2[List[Address], List[Int]] = {
      if (count == len) {
        (List.empty, List.empty)
      } else {
        val byteAddress = data.takeWhile(_ != 0)
        val newData = data.dropWhile(_ != 0).tail
        val newCount = count + 1
        val newPosition = byteAddress.length + 1
        val destFlag = byteAddress.head
        val nextIter = getAddresses(newData, newCount, len)
        if (destFlag == SMEAddressFlag) {
          (SMEAddress().fromBytes(byteAddress) :: nextIter._1, newPosition :: nextIter._2)
        } else {
          (DistributionList().fromBytes(byteAddress) :: nextIter._1, newPosition :: nextIter._2)
        }
      }
    }
  */

  def pack(): List[Int] = {
    val body: List[Int] = cstring2Binary(serviceType, 6) ++
      sourceAddress.getBytes ++
      sshort2Binary(destAddresses.length.asInstanceOf[Short]) ++
      SubmitMulti.destAddressesToBytes(destAddresses) ++
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

  def unpack(data: List[Int]) = {
    val (serviceType1: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (sourceAddrTon1, sourceAddrNpi1, data3: List[Int]) = (binary2SShort(data2.head), binary2SShort(data2.tail.head), data2.tail.tail)
    val (sourceAddr1: String, data4: List[Int]) = (binary2String(data3.takeWhile(_ != 0)), data3.dropWhile(_ != 0).tail)
    val (destAddressesLen: Short, data5: List[Int]) = (data4.head.asInstanceOf[Short], data4.tail)
    val (destAddresses1: List[Address], position: List[Int]) = SubmitMulti.getAddresses(data5, 0, destAddressesLen)
    val data6 = data5.drop(position.sum)
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

    SubmitMulti(serviceType1,
      sourceAddress1,
      destAddresses1,
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

case class SubmitMultiResponse(messageId: String, noUnsuccess: Short, unsuccessSME: List[Address]) extends PDUPacker {
  def pack(): List[Int] = {
    cstring2Binary(messageId, 65) ++
    sshort2Binary(noUnsuccess) ++
    SubmitMulti.destAddressesToBytes(unsuccessSME)
  }

  def unpack(data: List[Int]): PDUPacker = {
    val (messageId1: String, data2: List[Int]) = (binary2String(data.takeWhile(_ != 0)), data.dropWhile(_ != 0).tail)
    val (noUnsuccess1: Short, data3: List[Int]) = (binary2SShort(data.head), data.tail)
    val (unsuccessSME1: List[Address], _) = SubmitMulti.getAddresses(data3, 0, noUnsuccess1)
    SubmitMultiResponse(messageId1, noUnsuccess1, unsuccessSME1)
  }
}
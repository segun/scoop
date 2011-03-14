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

case class SubmitMulti(serviceType: String = "",
                       sourceAddress: Address = SMEAddress(),
                       destAddresses: Array[Address] = Array.empty,
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
                       tlv: Array[TLV] = Array.empty) extends PDUPacker with Submit {


  def getAddressesAsByte(): Array[Int] = {
    if (destAddresses.length <= MAX_DEST_ADDRESSES) {
      //val tlvs = (TLV().pack /: tlv)(_ ++ _.pack)
      (new Address().getBytes /: destAddresses) (_ ++ _.getBytes)
    } else {
      throw new MatchError("Destination Addresses should not exceed" + MAX_DEST_ADDRESSES + " but is " + destAddresses.length)
    }
  }

  def pack(): Array[Int] = {
    val body: Array[Int] = cstring2Binary(serviceType, 6) ++
      sourceAddress.getBytes ++
      getAddressesAsByte ++
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

  def unpack(data: Array[Int]) = {
    this
  }
}
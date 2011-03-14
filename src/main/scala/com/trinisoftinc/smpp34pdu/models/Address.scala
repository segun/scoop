package com.trinisoftinc.smpp34pdu.models

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/14/11
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */

import com.trinisoftinc.smpp34pdu.util.PDUData._
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

class Address(df: Int = 1) {
  def destFlag = df
  def getBytes: Array[Int] = {
    Array.empty
  }
}

case class SMEAddress(ton: Short = 0, npi: Short = 0, address: String = "", df: Int = 1) extends Address(df) {
  override def getBytes: Array[Int] = {
    if (destFlag == SMEAddressFlag) {
      sshort2Binary(ton) ++ sshort2Binary(npi) ++ cstring2Binary(address, 21)
    } else {
      throw new MatchError("Destination Flag for this Address should be " + SMEAddressFlag + ", but it's " + df)
    }
  }
}

case class DistributionList(dlName: String = "", df: Int = 2) extends Address(df) {
  override def getBytes: Array[Int] = {
    if(destFlag == DistributionListFlag) {
    cstring2Binary(dlName, 21)
    } else {
      throw new MatchError("Destination Flag for this Address should be " + DistributionListFlag + ", but it's " + df)
    }
  }
}


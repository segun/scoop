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

  def getBytes: List[Int] = {
    List.empty
  }

  def fromBytes(data: List[Int]): Address = {
    this
  }
}

case class UnsuccessfulAddress(ton: Short = 0, npi: Short = 0, address: String = "",
                               errorStatusCode: Int = ESME_ROK) extends Address {
  override def getBytes: List[Int] = {
      sshort2Binary(ton) ++ sshort2Binary(npi) ++ cstring2Binary(address, 21) ++ int2Binary(errorStatusCode)
  }

  override def fromBytes(data1: List[Int]): UnsuccessfulAddress = {
    val (sourceAddrTon1, sourceAddrNpi1, data2: List[Int]) = (binary2SShort(data1.head), binary2SShort(data1.tail.head), data1.tail.tail)
    val (sourceAddr1: String, errorStatusCode1) = (binary2String(data2.takeWhile(_ != 0)), binary2Int(data2.dropWhile(_ != 0).tail))
    UnsuccessfulAddress(sourceAddrTon1, sourceAddrNpi1, sourceAddr1, errorStatusCode1)
  }
}
case class SMEAddress(ton: Short = 0, npi: Short = 0, address: String = "", df: Int = 1) extends Address(df) {
  override def getBytes: List[Int] = {
    if (destFlag == SMEAddressFlag) {
      sshort2Binary(ton) ++ sshort2Binary(npi) ++ cstring2Binary(address, 21)
    } else {
      throw new MatchError("Destination Flag for this Address should be " + SMEAddressFlag + ", but it's " + df)
    }
  }

  override def fromBytes(data: List[Int]): SMEAddress = {
    val (destFlag: Short, data1: List[Int]) = (binary2SShort(data.head), data.tail)
    val (sourceAddrTon1, sourceAddrNpi1, data2: List[Int]) = (binary2SShort(data1.head), binary2SShort(data1.tail.head), data1.tail.tail)
    val sourceAddr1: String = binary2String(data2.takeWhile(_ != 0))
    SMEAddress(sourceAddrTon1, sourceAddrNpi1, sourceAddr1, destFlag)
  }
}

case class DistributionList(dlName: String = "", df: Int = 2) extends Address(df) {
  override def getBytes: List[Int] = {
    if (destFlag == DistributionListFlag) {
      cstring2Binary(dlName, 21)
    } else {
      throw new MatchError("Destination Flag for this Address should be " + DistributionListFlag + ", but it's " + df)
    }
  }

  override def fromBytes(data: List[Int]): DistributionList = {
    val (destFlag: Short, dlName1: String) = (binary2SShort(data.head), binary2String(data.tail.takeWhile(_ != 0)))
    DistributionList(dlName1, destFlag)
  }
}


package com.trinisoftinc.smpp34pdu.models

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.trinisoftinc.smpp34pdu.util.SMPPConstants._

/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/3/11
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */

class TestBindResponses extends FlatSpec with ShouldMatchers {

  "A BindTransmitterResponse PDU(with TLV)" should "equal an List of head ++ body (with TLV) when packed" in {
    val head: List[Int] = List(
      0, 0, 0, 32,
      128, 0, 0, 2,
      0, 0, 0, 0,
      0, 0, 0, 1
    )

    val body: List[Int] = List(97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 0, 2, 16, 0, 1, 52)

    val bindResponse = BindResponse("abcdefghij", TLV(ScInterfaceVersion, INTERFACE_VERSION))
    val expected = head ++ body
    val result = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal(expected)
  }

  it should "equal an List of head ++ body (without TLV) when packed" in {
    val head2: List[Int] = List(
      0, 0, 0, 27,
      128, 0, 0, 2,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body2: List[Int] = List(97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 0)

    val bindResponse = BindResponse("abcdefghij")
    val expected = head2 ++ body2
    val result = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal(expected)
  }

  "A BindTransmitterResponse PDU" should "equal BindTransmitterResponse when packed and unpacked" in {
    val bindResponse = BindResponse("abcdefghij", TLV(ScInterfaceVersion, INTERFACE_VERSION))
    val expected = PDU(BIND_TRANSMITTER_RESP, ESME_ROK, 0x00000001, bindResponse)
    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal(expected)
    result.pack should equal(expected.pack)
    pduPacker should equal(bindResponse)
  }

  "A BindRecieverResponse PDU(with TLV)" should "equal an List of head ++ body (with TLV) when packed" in {
    val head: List[Int] = List(
      0, 0, 0, 26,
      128, 0, 0, 1,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body: List[Int] = List(97, 98, 99, 100, 0, 2, 16, 0, 1, 52)
    val bindResponse = BindResponse("abcd", TLV(ScInterfaceVersion, INTERFACE_VERSION))
    val expected = head ++ body
    val result = PDU(BIND_RECEIVER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal(expected)
  }

  it should "equal and List of head ++ body (without TLV) when packed" in {
    val head2: List[Int] = List(
      0, 0, 0, 21,
      128, 0, 0, 1,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body2: List[Int] = List(97, 98, 99, 100, 0)
    val bindResponse = BindResponse("abcd")
    val expected = head2 ++ body2
    val result = PDU(BIND_RECEIVER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal(expected)
  }

  "A BindRecieverResponse PDU" should "equal BindRecieverResponse when packed and unpacked" in {
    val bindResponse = BindResponse("abcd", TLV(ScInterfaceVersion, INTERFACE_VERSION))
    val expected = PDU(BIND_RECEIVER_RESP, ESME_ROK, 0x00000001, bindResponse)
    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal(expected)
    result.pack should equal(expected.pack)
    pduPacker should equal(bindResponse)
  }

  "A BindTransceiverResponse PDU (with TLV)" should "equal an List of head ++ body (with TLV) when packed" in {
    val head: List[Int] = List(
      0, 0, 0, 26,
      128, 0, 0, 9,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body: List[Int] = List(97, 98, 99, 100, 0, 2, 16, 0, 1, 52)

    val bindResponse = BindResponse("abcd", TLV(ScInterfaceVersion, INTERFACE_VERSION))
    val expected = head ++ body
    val result = PDU(BIND_TRANSCEIVER_RESP, ESME_ROK, 0x00000001, bindResponse).pack
    result should equal(expected)
  }


  "A BindTransceiverResponse PDU" should "equal BindTransceiverResponse when packed and unpacked" in {
    val head: List[Int] = List(
      0, 0, 0, 26,
      128, 0, 0, 9,
      0, 0, 0, 0,
      0, 0, 0, 1
    )
    val body: List[Int] = List(97, 98, 99, 100, 0, 2, 16, 0)
    val bindResponse = BindResponse("abcd")
    val expected = PDU(BIND_TRANSCEIVER_RESP, ESME_ROK, 0x00000001, bindResponse)

    val (result, pduPacker) = expected.unpack(expected.pack)

    result should equal(expected)
    result.pack should equal(expected.pack)
    pduPacker should equal(bindResponse)
  }

}
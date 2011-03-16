/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

object SMPPConstants {
  val BIND_RECEIVER = 0x00000001
  val BIND_RECEIVER_RESP = 0x80000001
  val BIND_TRANSMITTER = 0x00000002
  val BIND_TRANSMITTER_RESP = 0x80000002
  val BIND_TRANSCEIVER = 0x00000009
  val BIND_TRANSCEIVER_RESP = 0x80000009
  val OUTBIND = 0x0000000B
  val UNBIND = 0x00000006
  val UNBIND_RESP = 0x80000006
  val GENERIC_NACK = 0x80000000
  val SUBMIT_SM = 0x00000004
  val SUBMIT_SM_RESP = 0x80000004
  val SUBMIT_MULTI = 0x00000021
  val SUBMIT_MULTI_RESP = 0x80000021

  val ESME_ROK = 0x00000000
  val ESME_RINVMSGLEN = 0x00000001
  val ESME_RINVCMDLEN = 0x00000002
  val ESME_RINVCMDID = 0x00000003
  val ESME_RINVBNDSTS = 0x00000004
  val ESME_RALYBND = 0x00000005
  val ESME_RINVPRTFLG = 0x00000006
  val ESME_RINVREGDLVFLG = 0x00000007
  val ESME_RSYSERR = 0x00000008
  val ESME_RINVSRCADR = 0x0000000A
  val ESME_RINVDSTADR = 0x0000000B
  val ESME_RINVMSGID = 0x0000000C
  val ESME_RBINDFAIL = 0x0000000D
  val ESME_RINVPASWD = 0x0000000E
  val ESME_RINVSYSID = 0x0000000F
  val ESME_RCANCELFAIL = 0x00000011
  val ESME_RREPLACEFAIL = 0x00000013
  val ESME_RMSGQFUL = 0x00000014
  val ESME_RINVSERTYP = 0x00000015
  val ESME_RINVNUMDESTS = 0x00000033
  val ESME_RINVDLNAME = 0x00000034
  val ESME_RINVDESTFLAG = 0x00000040
  val ESME_RINVSUBREP = 0x00000042
  val ESME_RINVESMCLASS = 0x00000043
  val ESME_RCNTSUBDL = 0x00000044
  val ESME_RSUBMITFAIL = 0x00000045
  val ESME_RINVSRCTON = 0x00000048
  val ESME_RINVSRCNPI = 0x00000049
  val ESME_RINVDSTTON = 0x00000050
  val ESME_RINVDSTNPI = 0x00000051
  val ESME_RINVSYSTYP = 0x00000053
  val ESME_RINVREPFLAG = 0x00000054
  val ESME_RINVNUMMSGS = 0x00000055
  val ESME_RTHROTTLED = 0x00000058
  val ESME_RINVSCHED = 0x00000061
  val ESME_RINVEXPIRY = 0x00000062
  val ESME_RINVDFTMSGID = 0x00000063
  val ESME_RX_T_APPN = 0x00000064
  val ESME_RX_P_APPN = 0x00000065
  val ESME_RX_R_APPN = 0x00000066
  val ESME_RQUERYFAIL = 0x00000067
  val ESME_RINVOPTPARSTREAM = 0x000000C0
  val ESME_ROPTPARNOTALLWD = 0x000000C1
  val ESME_RINVPARLEN = 0x000000C2
  val ESME_RMISSINGOPTPARAM = 0x000000C3
  val ESME_RINVOPTPARAMVAL = 0x000000C4
  val ESME_RDELIVERYFAILURE = 0x000000FE
  val ESME_RUNKNOWNERR = 0x000000FF


  val DestinationAddressSubunit = 0x0005
  val DestinationNetworkType = 0x0006
  val DestinationBearerType = 0x0007
  val DestinationTelematicsId = 0x0008
  val SourceAddressSubunit = 0x000D
  val SourceNetworkType = 0x000E
  val SourceBearerType = 0x000F
  val SourceTelematicsId = 0x0010
  val QosTTL = 0x0017
  val PayLoadType = 0x0019
  val AdditionalStatusInfoText = 0x001D
  val ReceiptedMessageId = 0x001E
  val MsMsgWaitFacilities = 0x0030
  val PrivacyIndicator = 0x0201
  val SourceSubAddress = 0x0202
  val DestinationSubAddress = 0x0203
  val UserMessageReference: Short = 0x0204
  val UserResponseCode = 0x0205
  val SourcePort = 0x020A
  val DestinationPort = 0x020B
  val SarMsgRefNum = 0x020C
  val LanguageIndicator = 0x020D
  val SarTotalSegments = 0x020E
  val SarSegmentSeqnum = 0x020F
  val ScInterfaceVersion: Short = 0x0210
  val CallBackNumPresInd = 0x0302
  val CallBackNumAtag = 0x0303
  val NumberOfMessages = 0x0304
  val CallBackNum = 0x0381
  val DpfResult = 0x0420
  val SetDpf = 0x0421
  val MsAvailabilityStatus = 0x0422
  val NetworkErrorCode = 0x0423
  val MessagePayload: Short = 0x0424
  val DeliveryFailureReason = 0x0425
  val MoreMessagesToSend = 0x0426
  val MessageState = 0x0427
  val UssdServiceOp = 0x0501
  val DisplayTime = 0x1201
  val SmsSignal = 0x1203
  val MsValidity = 0x1204
  val AlertOnMessageDelivery = 0x130C
  val ItsReplyType = 0x1308
  val ItsSessionInfo = 0x1383

  val MSDisplay = 0x01
  val MobileEquipment = 0x02
  val SmartCard1 = 0x03
  val ExternalUnit1 = 0x04

  val NotRestricted = 0
  val Restricted = 1
  val Confidential = 2
  val Secret = 3

  val StoreIndefinitely = 0
  val PowerDown = 1
  val SIDBasedRegistrationArea = 2
  val DisplayOnly = 3

  val Digit = 0
  val Number = 1
  val TelephoneNumber = 2
  val Password = 3
  val CharacterLine = 4
  val Menu = 5
  val Date = 6
  val Time = 7
  val Continue = 8

  val PSSDIndication = 0
  val PSSRIndication = 1
  val USSRRequest = 2
  val USSNRequest = 3
  val PSSDResponse = 16
  val PSSRResponse = 17
  val USSRConfirm = 18
  val USSNConfirm = 19

  val SMEAddressFlag: Short = 1
  val DistributionListFlag: Short = 2


  val DEFAULTCSTRING = ""
  val INTERFACE_VERSION: Short = 0x34
  val HEADEROCTETSIZE = 0x10 //16 in hexadecimal
  val MAX_DEST_ADDRESSES = 254
  val ZERO = 0x00000000
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.util

object SMPPConstants {
  val BIND_TRANSMITTER = 0x00000002

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


  val destinationAddressSubunit = 0x0005
  val destinationNetworkType = 0x0006
  val destinationBearerType = 0x0007
  val destinationTelematicsId = 0x0008
  val sourceAddressSubunit = 0x000D
  val sourceNetworkType = 0x000E
  val sourceBearerType = 0x000F
  val sourceTelematicsId = 0x0010
  val qosTTL = 0x0017
  val payLoadTime = 0x0019
  val additionalStatusInfoText = 0x001D
  val receiptedMessageId = 0x001E
  val msMsgWaitFacilities = 0x0030
  val privacyIndicator = 0x0201
  val sourceSubAddress = 0x0202
  val destinationSubAddress = 0x0203
  val userMessageReference = 0x0204
  val userResponseCode = 0x0205
  val sourcePort = 0x020A
  val destinationPort = 0x020B
  val sarMsgRefNum = 0x020C
  val languageIndicator = 0x020D
  val sarTotalSegments = 0x020E
  val sarSegmentSeqnum = 0x020F
  val scInterfaceVersion = 0x0210
  val callBackNumPresInd = 0x0302
  var callBackNumAtag = 0x0303
  var numberOfMessages = 0x0304
  var callBackNum = 0x0381
  var dpfResult = 0x0420
  var setDpf = 0x0421
  var msAvailabilityStatus = 0x0422
  var networkErrorCode = 0x0423
  var messagePayload = 0x0424
  var deliveryFailureReason = 0x0425
  var moreMessagesToSend = 0x0426
  var messageState = 0x0427
  var ussdServiceOp = 0x0501
  var displayTime = 0x1201
  var smsSignal = 0x1203
  var msValidity = 0x1204
  var alertOnMessageDelivery = 0x130C
  var itsReplyType = 0x1308
  var itsSessionInfo = 0x1383


  val DEFAULTCSTRING = ""
  val INTERFACE_VERSION: Short = 0x34
  val HEADEROCTETSIZE = 0x10 //16 in hexadecimal
}

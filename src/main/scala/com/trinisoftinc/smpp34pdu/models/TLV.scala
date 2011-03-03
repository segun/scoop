/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trinisoftinc.smpp34pdu.models

case class TLV(command: Int, tag: Int, length: Int, value: Any) {
  def pack = {

  }
}

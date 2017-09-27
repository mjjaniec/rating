package com.avsystem.lib

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, DataInputStream, DataOutputStream}
import java.security.{PrivateKey, PublicKey}
import java.util.Base64
import javax.crypto.Cipher

import com.avsystem.commons.serialization.{GenCodec, StreamInput, StreamOutput}

case class FeedbackRequest(product: String,
                           installation: String,
                           panelPath: String,
                           userDomain: String,
                           userId: String)

object FeedbackRequest {
  class Decoder(privateKey: PrivateKey) {
    private val cipher = Cipher.getInstance("RSA")

    def decode(encoded: String): FeedbackRequest = {
      cipher.init(Cipher.DECRYPT_MODE, privateKey)
      val cipherText = Base64.getDecoder.decode(encoded)
      val plainText = cipher.doFinal(cipherText)

      val bais = new ByteArrayInputStream(plainText)
      val in = new DataInputStream(bais)

      GenCodec.read[FeedbackRequest](new StreamInput(in))
    }
  }

  class Encoder(publicKey: PublicKey) {
    private val cipher = Cipher.getInstance("RSA")

    def encode(feedbackRequest: FeedbackRequest): String = {
      val baos = new ByteArrayOutputStream()
      val out = new DataOutputStream(baos)

      try {
        GenCodec.write(new StreamOutput(out), feedbackRequest)
        out.flush()
        baos.flush()

        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val plainText = baos.toByteArray
        val cipherText = cipher.doFinal(plainText)

        Base64.getEncoder.encodeToString(cipherText)
      } finally {
        out.close()
        baos.close()
      }
    }
  }

  implicit val codec: GenCodec[FeedbackRequest] = GenCodec.materialize[FeedbackRequest]
}

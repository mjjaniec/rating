package com.avsystem.lib

import java.io.File
import java.nio.file.Files
import java.security.{KeyFactory, PrivateKey, PublicKey}
import java.security.spec.{PKCS8EncodedKeySpec, X509EncodedKeySpec}

object KeyFileUtil {
  def loadPublicKey(filename: String): PublicKey = {
    val keyBytes = Files.readAllBytes(new File(filename).toPath)
    val spec = new X509EncodedKeySpec(keyBytes)
    val kf = KeyFactory.getInstance("RSA")
    kf.generatePublic(spec)
  }

  def loadPrivateKey(filename: String): PrivateKey = {
    val keyBytes = Files.readAllBytes(new File(filename).toPath)
    val spec = new PKCS8EncodedKeySpec(keyBytes)
    val kf = KeyFactory.getInstance("RSA")
    kf.generatePrivate(spec)
  }

}

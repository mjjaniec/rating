package com.avsystem.lib

import java.nio.file.{Files, Paths}
import java.security.{Key, KeyPairGenerator}

object KeyGen {

  def main(args: Array[String]): Unit = {
    val generator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(1024)

    val pair = generator.generateKeyPair()
    store(pair.getPrivate, "private.key")
    store(pair.getPublic, "public.key")
  }

  private def store(key: Key, filename: String): Unit = {
    Files.write(Paths.get(filename), key.getEncoded)
  }
}

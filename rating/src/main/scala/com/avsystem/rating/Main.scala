package com.avsystem.rating

import java.security.PrivateKey

import com.avsystem.common.VaadinJettyServer
import com.avsystem.lib.{FeedbackRequest, KeyFileUtil}

object Main {

  var feedbackRequestDecoder: FeedbackRequest.Decoder = _
  val opinions: MMap[RatingPanel, MMap[RatingUser, Opinion]] = MMap.empty

  def main(args: Array[String]): Unit = {
    val privateKey: PrivateKey = KeyFileUtil.loadPrivateKey("private.key")
    feedbackRequestDecoder = new FeedbackRequest.Decoder(privateKey)

    new VaadinJettyServer(classOf[RatingUI], 8888, widgetSet = "com.avsystem.rating.WidgetSet" ).start()
  }
}

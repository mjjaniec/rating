package com.avsystem.lib


class FeedbackButtonFactory[T](feedbackServer: String,
                               product: String,
                               installation: String,
                               encoder: FeedbackRequest.Encoder)
                              (uiComponentCreator: String => T) {

  def create(userDomain: String, userName: String, panelPath: String): T = {
    val feedbackRequest = FeedbackRequest(product, installation, panelPath, userDomain, userName)
    val serializedRequest = encoder.encode(feedbackRequest)

    uiComponentCreator.apply(s"$feedbackServer/$serializedRequest")
  }

}

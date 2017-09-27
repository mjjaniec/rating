package com.avsystem.rated

import java.security.PublicKey

import com.avsystem.common.VaadinJettyServer
import com.avsystem.lib.{FeedbackButtonFactory, FeedbackRequest, KeyFileUtil}
import com.vaadin.server.ExternalResource
import com.vaadin.ui.{Button, Link}

object Main {

  var feedbackFactory: FeedbackButtonFactory[Button] = _

  def main(args: Array[String]): Unit = {
    val publicKey: PublicKey = KeyFileUtil.loadPublicKey("public.key")

    feedbackFactory = new FeedbackButtonFactory[Button](
      "localhost:8888",
      "Rated",
      "Test",
      new FeedbackRequest.Encoder(publicKey)
    )(url => {
      val link = new Button()
      link.addClickListener(_ => link.getUI.getPage.open(url, "_blank"))
      link.setCaption("Send us your feedback")
      link
    })

    new VaadinJettyServer(classOf[RatedUI], 8000).start()
  }
}

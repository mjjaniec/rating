package com.avsystem.rating

import com.vaadin.server.VaadinRequest
import com.vaadin.ui._

class RatingUI extends UI {

  override def init(request: VaadinRequest): Unit = {

    val encodedRequest = request.getPathInfo.substring(1)
    val feedbackRequest = Main.feedbackRequestDecoder.decode(encodedRequest)

    val ratingPanel = RatingPanel(feedbackRequest.product, feedbackRequest.panelPath)
    val ratingUser = RatingUser(feedbackRequest.product, feedbackRequest.installation,
      feedbackRequest.userDomain, feedbackRequest.userId)

    val allOptions = Main.opinions.getOrElseUpdate(ratingPanel, MMap.empty[RatingUser, Opinion])
    val userOpinion = allOptions.get(ratingUser)

    setContent(new PanelView(ratingPanel, userOpinion.toOpt, allOptions.values, opinion => allOptions.put(ratingUser, opinion)))
  }
}

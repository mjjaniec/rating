package com.avsystem.rating

import com.avsystem.commons.misc.Opt

case class Opinion(usability: Int, appearance: Int, performance: Int, comment: Opt[String], nickname: Opt[String])

case class RatingPanel(product: String, panelName: String)
case class RatingUser(product: String, installation: String, domain: String, userId: String)

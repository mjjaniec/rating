package com.avsystem.rating

import com.avsystem.commons.misc.Opt
import com.vaadin.ui._
import org.vaadin.teemu.ratingstars.RatingStars

class PanelView(ratingPanel: RatingPanel, currentOption: Opt[Opinion], allOpinions: Iterable[Opinion], addOpinion: Opinion => Unit) extends VerticalLayout {

  {
    if (currentOption.isEmpty) {
      addComponent(new Label(s"How do you like ${ratingPanel.panelName}?"))
      val layout = new FormLayout()
      layout.addComponent(new RatingStars().setup(_.setCaption("Appearance")))
      layout.addComponent(new RatingStars().setup(_.setCaption("Usability")))
      layout.addComponent(new RatingStars().setup(_.setCaption("Performance")))
      layout.addComponent(new TextArea().setup(_.setCaption("Your opinion")))
      layout.addComponent(new Button("Submit").setup(_.addClickListener(_ => {
        val opinion = Opinion(1, 2, 3, Opt.empty, Opt.empty)
        addOpinion(opinion)
        layout.removeAllComponents()
        layout.addComponent(new Label("Thank you"))
      })))
      addComponent(layout)
    } else {
      addComponent(new Label("You've posted an opinion already."))
    }

    addComponent(new Label())
  }
}

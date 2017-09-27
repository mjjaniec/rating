package com.avsystem.rated

import com.vaadin.server.VaadinRequest
import com.vaadin.ui._
import com.vaadin.ui.themes.ValoTheme

class RatedUI extends UI {

  override def init(request: VaadinRequest): Unit = {
    val layout = new VerticalLayout()
    val label = new Label("Test panel")
    label.addStyleName(ValoTheme.LABEL_H2)
    layout.addComponent(label)
    layout.addComponent(new Label("This is a panel to be rated"))
    layout.addComponent({
      val btn = Main.feedbackFactory.create("User domain", "First user", "Test panel")
      btn.setCaption(btn.getCaption + "[First user]")
      btn
    } )

    layout.addComponent({
      val btn = Main.feedbackFactory.create("User domain", "Second user", "Test panel")
      btn.setCaption(btn.getCaption + "[Second user]")
      btn
    } )
    setContent(layout)
  }
}

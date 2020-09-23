package app.sonderful.somewhatalarmed.presentation.detail

import app.sonderful.somewhatalarmed.types.Alarm
import tornadofx.View
import tornadofx.*

class AlarmDetailView : View("Alarm Details") {

    val currentAlarm: Alarm by param()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Alarm: ${currentAlarm.tag}")
    }
}
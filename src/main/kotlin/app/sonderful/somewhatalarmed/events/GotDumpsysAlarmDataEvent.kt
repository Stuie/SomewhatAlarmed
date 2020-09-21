package app.sonderful.somewhatalarmed.events

import tornadofx.FXEvent

class GotDumpsysAlarmDataEvent(val data: String) : FXEvent()
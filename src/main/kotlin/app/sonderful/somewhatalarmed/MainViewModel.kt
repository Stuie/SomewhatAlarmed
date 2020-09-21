package app.sonderful.somewhatalarmed

import app.sonderful.somewhatalarmed.events.GotDumpsysAlarmDataEvent
import tornadofx.ViewModel

class MainViewModel : ViewModel() {
    init {
        // TODO Call `adb shell dumpsys alarm` and get all data

        fire(GotDumpsysAlarmDataEvent("test"))
    }
}
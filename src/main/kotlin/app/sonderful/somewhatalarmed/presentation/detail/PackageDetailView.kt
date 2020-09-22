package app.sonderful.somewhatalarmed.presentation.detail

import app.sonderful.somewhatalarmed.types.Alarm
import app.sonderful.somewhatalarmed.types.AlarmManagerState
import app.sonderful.somewhatalarmed.types.Package
import tornadofx.View
import tornadofx.*

class PackageDetailView : View() {

    private val viewModel: PackageDetailViewModel by inject()

    val currentPackage: Package by param()
    val alarmManagerState: AlarmManagerState by param()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Pending Alarms for ${currentPackage.name} (${currentPackage.uid})")
        tableview(viewModel.pendingAlarms) {
            readonlyColumn("Tag", Alarm::tag).prefWidth(350)
            readonlyColumn("Type", Alarm::type).prefWidth(75)
        }
    }

    override fun onDock() {
        super.onDock()
        viewModel.setup(currentPackage, alarmManagerState)
    }
}
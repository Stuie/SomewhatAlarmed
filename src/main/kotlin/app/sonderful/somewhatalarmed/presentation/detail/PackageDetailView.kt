package app.sonderful.somewhatalarmed.presentation.detail

import app.sonderful.somewhatalarmed.types.Alarm
import app.sonderful.somewhatalarmed.types.AlarmManagerState
import app.sonderful.somewhatalarmed.types.Package
import javafx.beans.binding.Bindings
import tornadofx.View
import tornadofx.*

class PackageDetailView : View("Package Details") {

    private val viewModel: PackageDetailViewModel by inject()

    val currentPackage: Package by param()
    val alarmManagerState: AlarmManagerState by param()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Package: ${currentPackage.name} (${currentPackage.uid})")
        val tv = tableview(viewModel.pendingAlarms) {
            tooltip("Pending alarms")
            readonlyColumn("Tag", Alarm::tag).prefWidth(350)
            readonlyColumn("Type", Alarm::type).prefWidth(75)
            onUserSelect {
                viewModel.loadDetailsForAlarm(it)
            }
        }
        button("See Details") {
            disableProperty().bind(Bindings.isEmpty(tv.selectionModel.selectedItems))
            action {
                tv.selectedItem?.let {
                    viewModel.loadDetailsForAlarm(it)
                }
            }
        }
    }

    override fun onDock() {
        super.onDock()
        viewModel.setup(currentPackage, alarmManagerState)
    }
}
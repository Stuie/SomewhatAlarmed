package app.sonderful.somewhatalarmed.presentation.detail

import app.sonderful.somewhatalarmed.types.Alarm
import app.sonderful.somewhatalarmed.types.AlarmManagerState
import app.sonderful.somewhatalarmed.types.Package
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ViewModel

class PackageDetailViewModel : ViewModel() {

    var currentPackage: Package? = null
    var alarmManagerState: AlarmManagerState? = null

    val pendingAlarms: ObservableList<Alarm> = FXCollections.observableArrayList()

    fun setup(currentPackage: Package, alarmManagerState: AlarmManagerState) {
        this.currentPackage = currentPackage
        this.alarmManagerState = alarmManagerState

        pendingAlarms.clear()

        alarmManagerState.batches.forEach { batch ->
            batch.alarms.filter { it.packageName.name == currentPackage.name }.forEach { alarm ->
                pendingAlarms.add(alarm)
            }
        }
    }
}
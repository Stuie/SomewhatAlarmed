/*
 * Copyright (C) 2020 Stuart Gilbert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    // TODO Replace this with something better when you understand TornadoFX's injection/param stuff better
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

    fun loadDetailsForAlarm(selectedAlarm: Alarm) {
        find<AlarmDetailView>(mapOf(AlarmDetailView::currentAlarm to selectedAlarm)).openWindow()
    }
}
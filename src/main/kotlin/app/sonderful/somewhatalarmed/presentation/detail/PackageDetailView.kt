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
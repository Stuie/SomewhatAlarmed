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
package app.sonderful.somewhatalarmed.presentation.load

import app.sonderful.somewhatalarmed.DumpsysAlarmParser
import app.sonderful.somewhatalarmed.presentation.detail.PackageDetailView
import app.sonderful.somewhatalarmed.types.AlarmManagerState
import app.sonderful.somewhatalarmed.types.Package
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.View
import tornadofx.ViewModel
import tornadofx.ViewTransition
import tornadofx.seconds
import java.io.File
import java.nio.charset.Charset

class LoadViewModel : ViewModel() {

    private var alarmManagerState: AlarmManagerState? = null

    val packages: ObservableList<Package> = FXCollections.observableArrayList()

    fun readAndParseFile(inputFile: File) {
        val input = inputFile.readText(Charset.defaultCharset())

        alarmManagerState = DumpsysAlarmParser().parse(input)

        with(packages) {
            clear()
            addAll(alarmManagerState?.packages.orEmpty())
        }
    }

    fun loadDetailsForPackage(currentView: View, selectedPackage: Package) {
        currentView.close()
        find<PackageDetailView>(mapOf(PackageDetailView::currentPackage to selectedPackage, PackageDetailView::alarmManagerState to alarmManagerState)).openWindow()
    }
}
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

import app.sonderful.somewhatalarmed.types.Package
import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.stage.FileChooser
import tornadofx.*

/**
 * When the application starts the first requirement is to load the dumpsys alarm data. The only way to load those data
 * at present is to read a text file.
 *
 * This view is responsible for loading the data and letting the user select the package they're interested in.
 */
class LoadView : View("Somewhat Alarmed") {

    private val viewModel: LoadViewModel by inject()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        hbox {
            style {
                spacing = 5.px
                padding = box(20.px)
                alignment = Pos.CENTER_LEFT
            }
            button("Choose Input File") {
                action {
                    val filename = chooseFile(
                        "Select output of dumpsys alarm",
                        arrayOf(
                            FileChooser.ExtensionFilter("Text files", "*.txt"),
                            FileChooser.ExtensionFilter("All files", "*"),
                        ),
                        null,
                        null,
                        FileChooserMode.Single
                    )

                    viewModel.readAndParseFile(filename[0])
                }
            }
            label("Load the output of dumpsys alarm")
        }
        label("Detected Packages")
        val tv = tableview(viewModel.packages) {
            tooltip("Choose a package to load alarm details")
            selectionModel.selectionMode = SelectionMode.SINGLE
            readonlyColumn("UID", Package::uid).prefWidth(75)
            readonlyColumn("Name", Package::name).prefWidth(350)
            onUserSelect {
                viewModel.loadDetailsForPackage(this@LoadView, it)
            }
        }
        button("See Details") {
            disableProperty().bind(Bindings.isEmpty(tv.selectionModel.selectedItems))
            action {
                tv.selectedItem?.let {
                    viewModel.loadDetailsForPackage(this@LoadView, it)
                }
            }
        }
    }
}

/**
 * Entry point for the application.
 */
class Launcher : App(LoadView::class)
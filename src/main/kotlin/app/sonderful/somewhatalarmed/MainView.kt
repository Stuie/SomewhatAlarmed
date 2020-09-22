package app.sonderful.somewhatalarmed

import app.sonderful.somewhatalarmed.types.Package
import javafx.beans.binding.Bindings
import javafx.scene.control.SelectionMode
import javafx.stage.FileChooser
import tornadofx.*

class MainView : View("Somewhat Alarmed") {

    private val viewModel: MainViewModel by inject()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Load the output of `adb shell dumpsys alarm`")
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
        label("Detected Packages")
        val tv = tableview(viewModel.packages) {
            tooltip("Choose a package to load alarm details")
            selectionModel.selectionMode = SelectionMode.SINGLE
            readonlyColumn("UID", Package::uid)
            readonlyColumn("Name", Package::name)
            onUserSelect {
                viewModel.loadDetailsForPackage(it)
            }
        }
        button("See Details") {
            disableProperty().bind(Bindings.isEmpty(tv.selectionModel.selectedItems))
            action {
                tv.selectedItem?.let {
                    viewModel.loadDetailsForPackage(it)
                }
            }
        }
    }
}

class Launcher : App(MainView::class)
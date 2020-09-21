package app.sonderful.somewhatalarmed

import tornadofx.*

class MainView : View("Somewhat Alarmed") {

    val viewModel: MainViewModel by inject()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Mice detected in registry")
//        miceListView = listview(controller.values) {
//            selectionModel.selectionMode = SelectionMode.MULTIPLE
//            style {
//                prefHeight = 200.px
//                prefWidth = 250.px
//            }
//            tooltip("Hold control or shift to select multiple mice")
//        }
//        button {
//            label("Invert Scroll")
//            style {
//                spacing = 5.px
//            }
//            action {
//                Platform.runLater {
//                    controller.invertScroll(miceListView.selectionModel.selectedIndices)
//                }
//            }
//        }
    }
}

class Launcher : App(MainView::class)
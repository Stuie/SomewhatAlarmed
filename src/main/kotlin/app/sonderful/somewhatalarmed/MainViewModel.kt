package app.sonderful.somewhatalarmed

import app.sonderful.somewhatalarmed.types.Package
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ViewModel
import java.io.File
import java.nio.charset.Charset

class MainViewModel : ViewModel() {

    val packages: ObservableList<Package> = FXCollections.observableArrayList()

    fun readAndParseFile(inputFile: File) {
        val input = inputFile.readText(Charset.defaultCharset())

        val alarmManagerState = DumpsysAlarmParser().parse(input)

        with(packages) {
            clear()
            addAll(alarmManagerState.packages)
        }
    }

    fun loadDetailsForPackage(selectedPackage: Package) {
        // TODO Navigate to a new view with some info
    }
}
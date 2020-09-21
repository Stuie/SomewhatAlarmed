package app.sonderful.somewhatalarmed.types

data class AlarmManagerState(val settings: List<Setting>, val batches: List<Batch>, val packages: List<Package>)
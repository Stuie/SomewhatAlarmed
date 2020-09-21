package app.sonderful.somewhatalarmed.types

data class Package(
    val uid: String = "UNKNOWN",
    val name: String = "UNKNOWN",
    val alarmStats: List<AlarmStats> = emptyList(),
)
package app.sonderful.somewhatalarmed.types

/**
    +527ms 2 wakes 2 alarms, last -4h9m6s28ms:
     *walarm*:org.thoughtcrime.securesms/.service.DirectoryRefreshListener
    +283ms 2 wakes 2 alarms, last -3h55m57s520ms:
     *walarm*:org.thoughtcrime.securesms/.service.RotateSenderCertificateListener
    +254ms 2 wakes 2 alarms, last -3h50m47s852ms:
     *walarm*:org.thoughtcrime.securesms/.service.LocalBackupListener
 */
data class AlarmStats(
    val tag: String,
    val runtime: String,
    val wakeCount: Int,
    val alarmCount: Int,
    val last: String
)
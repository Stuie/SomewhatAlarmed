package app.sonderful.somewhatalarmed.types

/**
 *     ELAPSED_WAKEUP #2: Alarm{9af7903 type 2 when 315360108243 com.google.android.gms}
 *       tag=*walarm*:com.google.android.gms/com.google.android.location.internal.GoogleLocationManagerService
 *       type=2 expectedWhenElapsed=+3648d19h50m58s839ms expectedMaxWhenElapsed=+6386d7h50m58s838ms whenElapsed=+3648d19h50m58s839ms maxWhenElapsed=+6386d7h50m58s838ms when=+3648d19h50m58s839ms
 *       window=+2737d11h59m59s999ms repeatInterval=0 count=0 flags=0x0
 *       operation=PendingIntent{1e7a080: PendingIntentRecord{58686b9 com.google.android.gms startService}}
 */
data class Alarm(
    val id: String,
    val type: AlarmType,
    val index: Int,
    val timeWhen: Long,
    val packageName: Package,
    val tag: String,
    val expectWhenElapsed: String,
    val expectedMaxWhenElapsed: String,
    val whenElapsed: String,
    val maxWhenElapsed: String,
    val timeWhenHumanReadable: String,
    val window: String,
    val repeatInterval: Int,
    val count: Int,
    val flags: String,
    val operation: String,
    val listener: String?
)
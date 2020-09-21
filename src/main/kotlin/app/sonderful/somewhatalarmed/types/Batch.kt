package app.sonderful.somewhatalarmed.types

/**
Batch{c64e97f num=2 start=381026457 end=549568754 flgs=0x8}:
    RTC #1: Alarm{e35371f type 1 when 1600846195654 com.google.android.music}
      tag=*alarm*:com.google.android.music.sync.EXP_ALARM
      type=1 expectedWhenElapsed=+3d5h39m37s53ms expectedMaxWhenElapsed=+5d19h1m4s986ms whenElapsed=+3d5h39m37s53ms maxWhenElapsed=+5d19h1m4s986ms when=2020-09-23 00:29:55.654
      window=+2d13h21m27s933ms repeatInterval=0 count=0 flags=0x0
      operation=PendingIntent{46e1a6c: PendingIntentRecord{a765835 com.google.android.music broadcastIntent}}
    ELAPSED #0: Alarm{d564d4c type 3 when 355168755 android}
      tag=*alarm*:LockSettingsStrongAuth.timeoutForUser
      type=3 expectedWhenElapsed=+2d22h28m39s351ms expectedMaxWhenElapsed=+5d4h28m39s350ms whenElapsed=+2d22h28m39s351ms maxWhenElapsed=+5d4h28m39s350ms when=+2d22h28m39s351ms
      window=+2d5h59m59s999ms repeatInterval=0 count=0 flags=0x8
      operation=null
      listener=android.app.AlarmManager$ListenerWrapper@e144195
 */
data class Batch(val id: String, val alarmCount: Int, val start: Long, val end: Long, val alarms: List<Alarm>)
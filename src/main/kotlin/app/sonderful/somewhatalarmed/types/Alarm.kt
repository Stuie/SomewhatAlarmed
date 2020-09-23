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
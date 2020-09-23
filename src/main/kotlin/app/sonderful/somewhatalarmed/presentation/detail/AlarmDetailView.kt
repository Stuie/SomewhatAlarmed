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
package app.sonderful.somewhatalarmed.presentation.detail

import app.sonderful.somewhatalarmed.types.Alarm
import javafx.scene.text.TextAlignment
import tornadofx.View
import tornadofx.*

class AlarmDetailView : View("Alarm Details") {

    val currentAlarm: Alarm by param()

    override val root = vbox {
        style {
            spacing = 5.px
            padding = box(20.px)
        }
        label("Alarm: ${currentAlarm.tag}")
        hbox {
            style {
                spacing = 5.px
            }
            label("Alarm Type") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.type}") {
                tooltip("`type` refers to the type of alarm\n\nRTC = Real Time Clock\nELAPSED = Time since device reboot\nRTC_WAKEUP and ELAPSED_WAKEUP are the same as above, but specifically intended to wake up the device.")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("When") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.timeWhen}") {
                tooltip("`when` refers to the time when this alarm should be triggered.\nThis may be inaccurate if your alarm is batched with other alarms, as in that case the latest `when` in the batched alarms will be used.")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("When Elapsed") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.whenElapsed}") {
                tooltip("`whenElapsed` refers to the approximate number of milliseconds since device boot at which the alarm will be triggered.")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("Repeat Interval") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.repeatInterval}") {
                tooltip("`repeatInterval` represents the numbber of milliseconds between successive triggers of the alarm. 0 means the alarm does not repeat.")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("Count") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.count}") {
                tooltip("`count` refers to the number of times the alarm should have been triggered, but was not.`")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("Operation") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.operation}") {
                tooltip("`operation` refers to what will happen when the alarm is triggered, e.g. a PendingIntent that will be triggered.\nThis may be null.")
            }
        }
        hbox {
            style {
                spacing = 5.px
            }
            label("Listener") {
                prefWidth = 200.0
                textAlignment = TextAlignment.LEFT
            }
            label("${currentAlarm.listener}") {
                tooltip("`listener` refers to what action will be taken when the alarm is triggered.\nThis may be null. I've only seen it set when `operation` is null.")
            }
        }
    }
}
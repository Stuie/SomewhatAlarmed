Somewhat Alarmed is a tool to help demystify alarms on Android devices.

The primary source of this information is an obscure command you can run in your terminal: `adb shell dumpsys alarm`. Unfortunately, the output of that command is pretty cryptic and unwieldy. It can be tough to find the output that is pertinent to your own application, and even when you do, it can be hard to understand. Somewhat Alarmed is intended to make it easy to confirm that your app alarms are in the correct state.

Running
-------

If you're familiar with developing Android applications, this should be pretty easy.

This application was written using Kotlin and TornadoFX, and so it can be run on Linux, Mac, and Windows where a modern version of the Java Runtime Environment is present. It has only been thoroughly tested on Windows with Amazon Corretto 11 (Open JDK).

Clone this repository, enter the directory and run `./gradlew run` on Linux and Mac, or `gradlew.bat run` on Windows.

How to Use
----------

The application can currently only load the data it needs from a text file. You need to run `adb shell dumpsys alarm` and save the output to a file. Then you can select that file in Somewhat Alarmed. If everything worked correctly, you'll see a list of packages. If you don't see the package you're interested in, then either the input was not recognized, or there are no alarms for that package.

Double-clicking the package in the list, or selecting it and pressing the "See Details" button will take you to a screen with a list of alarms associated with that package. Double-clicking the alarm in the list, or highligting it and pressing the "See Details" button will take you to a screen contaaining details about that alarm.

Acknowledgements
----------------

I found a repository called [DumpsysAlarm](https://github.com/Dottorhouse/DumpsysAlarm) by Dario R. on [an old StackOverflow thread](https://stackoverflow.com/questions/28742884/how-to-read-adb-shell-dumpsys-alarm-output), and I thought it would do what I wanted. Unfortunately, I'm not familiar with the tooling, and the project and associated account appear to have been abandoned for the last few years. I didn't base any of my code on any code found in that repository, but the idea is certainly the same.

License
-------

    Copyright 2020 Stuart Gilbert

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
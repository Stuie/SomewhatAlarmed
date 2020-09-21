package app.sonderful.somewhatalarmed

import app.sonderful.somewhatalarmed.types.*
import java.util.regex.Pattern

class DumpsysAlarmParser {

    fun parse(input: String): AlarmManagerState {
        val settings = getSettings(input)
        val batches = getBatches(input)
        val packages = getPackages(input)

        return AlarmManagerState(settings, batches, packages)
    }

    private fun getSettings(input: String): List<Setting> {
        val settingsList = mutableListOf<Setting>()

        val regex = Pattern.compile(
            " {2}Settings:\\r?\\n(.+?)(?:\\r?\\n){2}",
            Pattern.MULTILINE or Pattern.DOTALL
        )
        val matcher = regex.matcher(input)

        if (matcher.find()) {
            val settingsText = matcher.group(1)

            settingsText.split("\\r?\\n".toRegex()).forEach { line ->
                val (k, v) = line.split("=")
                settingsList.add(Setting(k, v))
            }
        }

        return settingsList
    }

    private fun getBatches(input: String): List<Batch> {
        val batchList = mutableListOf<Batch>()

        val regex = Pattern.compile(
            " {2}Pending alarm batches: ([0-9]+)\\r?\\n(.+?)(?:\\r?\\n){2}",
            Pattern.MULTILINE or Pattern.DOTALL
        )
        val matcher = regex.matcher(input)

        if (matcher.find()) {
            val batchCount = matcher.group(1)
            val batchText = matcher.group(2)

            batchText.split("^Batch".toRegex(RegexOption.MULTILINE)).filter { it.isNotEmpty() }.forEach { batchItem ->
                val batchInfoRegex = Pattern.compile(
                    "\\{([0-9a-f]+) num=([0-9]+) start=([0-9]+) end=([0-9]+)(?: flgs=([^}]+))?}",
                    Pattern.MULTILINE or Pattern.DOTALL
                )
                val batchMatcher = batchInfoRegex.matcher(batchItem)

                if (batchMatcher.find()) {
                    batchList.add(Batch(
                        batchMatcher.group(1),
                        batchMatcher.group(2).toInt(),
                        batchMatcher.group(3).toLong(),
                        batchMatcher.group(4).toLong(),
                        getAlarms(batchItem)))
                }
            }
        }

        return batchList
    }

    private fun getAlarms(input: String): List<Alarm> {
        val alarmList = mutableListOf<Alarm>()

        input.split(
            "^ {4}(ELAPSED|RTC|ELAPSED_WAKEUP|RTC_WAKEUP) ".toRegex(RegexOption.MULTILINE)
        ).forEachIndexed { index, alarmItem ->
            if (index != 0) {
                val alarmRegex = Pattern.compile(
                    "#([0-9]+): Alarm\\{([0-9a-f]+) type ([0-3]) when ([0-9]+) ([^}]+)}\\r?\\n {6}" +
                            "tag=\\*w?alarm\\*:([^\\r\\n]+?)\\r?\\n {6}type=\\d expectedWhenElapsed=(.+?) " +
                            "expectedMaxWhenElapsed=(.+?) whenElapsed=(.+?) maxWhenElapsed=(.+?) when=(.+?)\\r?\\n" +
                            " {6}window=(.+?) repeatInterval=(\\d+) count=(\\d+) flags=(.+?)\\r?\\n" +
                            " {6}operation=([^\\r\\n]+)\\r?\\n?(?: {6}listener=([^\\r\\n]+)?)?\\r?\\n?",
                    Pattern.MULTILINE or Pattern.DOTALL
                )
                val alarmMatcher = alarmRegex.matcher(alarmItem)

                if (alarmMatcher.find()) {
                    alarmList.add(
                        Alarm(
                            id = alarmMatcher.group(2),
                            type = AlarmType.values()[alarmMatcher.group(3).toInt()],
                            index = alarmMatcher.group(1).toInt(),
                            timeWhen = alarmMatcher.group(4).toLong(),
                            packageName = Package(name = alarmMatcher.group(5)),
                            tag = alarmMatcher.group(6),
                            expectWhenElapsed = alarmMatcher.group(7),
                            expectedMaxWhenElapsed = alarmMatcher.group(8),
                            whenElapsed = alarmMatcher.group(9),
                            maxWhenElapsed = alarmMatcher.group(10),
                            timeWhenHumanReadable = alarmMatcher.group(11),
                            window = alarmMatcher.group(12),
                            repeatInterval = alarmMatcher.group(13).toInt(),
                            count = alarmMatcher.group(14).toInt(),
                            flags = alarmMatcher.group(15),
                            operation = alarmMatcher.group(16),
                            listener = alarmMatcher.group(17)
                        )
                    )
                }
            }
        }

        return alarmList
    }

    private fun getPackages(input: String): List<Package> {
        val packageList = mutableListOf<Package>()

        val packageRegex = Pattern.compile(
            " {2}Alarm Stats:\\r?\\n(.+?)(?:\\r?\\n){2}",
            Pattern.MULTILINE or Pattern.DOTALL
        )
        val packageMatcher = packageRegex.matcher(input)

        if (packageMatcher.find()) {
            val packageText = packageMatcher.group(1).replace(" {2}(u[0-9a-f]+|[0-9]+):".toRegex(), "SPLITTER\$1:")
            packageText.split("SPLITTER").filter { it.isNotEmpty() }.forEach { packageItem ->
                val packageInfoRegex = Pattern.compile(
                    "(u[0-9a-f]+|[0-9]+):(.+?) \\+(.+?) running, (\\d+) wakeups:\r?\n(.+)",
                    Pattern.MULTILINE or Pattern.DOTALL
                )
                val packageInfoMatcher = packageInfoRegex.matcher(packageItem)

                if (packageInfoMatcher.find()) {
                    packageList.add(
                        Package(
                            uid = packageInfoMatcher.group(1),
                            name = packageInfoMatcher.group(2),
                            alarmStats = getAlarmStats(packageInfoMatcher.group(5))
                        )
                    )
                }
            }
        }

        return packageList
    }

    private fun getAlarmStats(input: String): List<AlarmStats> {
        val alarmStatsList = mutableListOf<AlarmStats>()

        input
            .replace(
                " {4}\\+".toRegex(RegexOption.MULTILINE),
                "SPLITTER+"
            )
            .split("SPLITTER")
            .filter { it.isNotEmpty() }
            .forEach { alarmStatsText ->
                val alarmStatsInfoRegex = Pattern.compile(
                    "^+(.+?) (\\d+) wakes (\\d+) alarms, last (.+?):\r?\n {6}\\*w?alarm\\*:(.+?)\r?\n?$",
                    Pattern.MULTILINE or Pattern.DOTALL
                )
                val alarmStatsInfoMatcher = alarmStatsInfoRegex.matcher(alarmStatsText)

                if (alarmStatsInfoMatcher.find()) {
                    alarmStatsList.add(
                        AlarmStats(
                            tag = alarmStatsInfoMatcher.group(5),
                            runtime = alarmStatsInfoMatcher.group(1),
                            wakeCount = alarmStatsInfoMatcher.group(2).toInt(),
                            alarmCount = alarmStatsInfoMatcher.group(3).toInt(),
                            last = alarmStatsInfoMatcher.group(4)
                        )
                    )
                }
            }

        return alarmStatsList
    }
}

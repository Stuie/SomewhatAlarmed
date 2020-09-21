package app.sonderful.somewhatalarmed

import app.sonderful.somewhatalarmed.types.AlarmType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class DumpsysAlarmParserTest {

    private val fullDumpResource = {}.javaClass.getResource("/strings/dumpsys_alarm_full.txt").readText()

    lateinit var subject: DumpsysAlarmParser

    @BeforeEach
    fun setup() {
        subject = DumpsysAlarmParser()
    }

    @Test
    fun `parse can get the entire settings block`() {
        val result = subject.parse(fullDumpResource)

        assertEquals(20, result.settings.size)
    }

    @Test
    fun `parse can get the pending alarm batches`() {
        val result = subject.parse(fullDumpResource)

        assertTrue(result.batches.isNotEmpty())
    }

    @Test
    fun `parse can get the alarms for the batches`() {
        val result = subject.parse(fullDumpResource)

        assertTrue(result.batches[0].alarms.isNotEmpty())
    }

    @Test
    fun `parse gets the correct alarm type for elapsed`() {
        val result = subject.parse(fullDumpResource)

        val type = result.batches.first { it.id == "d76efe8" }.alarms.first { it.id == "e2d0601" }.type

        assertEquals(AlarmType.ELAPSED, type)
    }

    @Test
    fun `parse gets the correct alarm type for elapsed wakeup`() {
        val result = subject.parse(fullDumpResource)

        val type = result.batches.first { it.id == "6c60ca6" }.alarms.first { it.id == "1315b69" }.type

        assertEquals(AlarmType.ELAPSED_WAKEUP, type)
    }

    @Test
    fun `parse gets the correct alarm type for rtc`() {
        val result = subject.parse(fullDumpResource)

        val type = result.batches.first { it.id == "83866e2" }.alarms.first { it.id == "428da73" }.type

        assertEquals(AlarmType.RTC, type)
    }

    @Test
    fun `parse gets the correct alarm type for rtc wakeup`() {
        val result = subject.parse(fullDumpResource)

        val alarms = result.batches.first { it.id == "4d91bc1" }.alarms
        val type = alarms.first { it.id == "b22067b" }.type

        assertEquals(AlarmType.RTC_WAKEUP, type)
    }

    @Test
    fun `parse gets the correct package name`() {
        val result = subject.parse(fullDumpResource)

        val alarms = result.batches.first { it.id == "4d91bc1" }.alarms
        val packageName = alarms.first { it.id == "b22067b" }.packageName

        assertEquals("com.amazon.clouddrive.photos", packageName.name)
    }

    @Test
    fun `parse builds package list with uids and package names`() {
        val result = subject.parse(fullDumpResource)

        val packageItem = result.packages.first { it.name == "app.sonderful.frige" }

        assertEquals("u0a320", packageItem.uid)
    }

    @Test
    fun `parse builds package list with alarm stats`() {
        val result = subject.parse(fullDumpResource)

        val packageItem = result.packages.first { it.name == "app.sonderful.frige" }
        val firstAlarmStats = packageItem.alarmStats[0]

        assertEquals("+88ms", firstAlarmStats.runtime)
        assertEquals("app.sonderful.frige.notifications.ShowNotification", firstAlarmStats.tag)
    }

    @Test
    fun `parse has null listener for alarm when not present`() {
        val result = subject.parse(fullDumpResource)

        val batch = result.batches.first { it.id == "c64e97f" }
        val alarm = batch.alarms.first { it.id == "e35371f" }

        assertNull(alarm.listener)
    }

    @Test
    fun `parse gets listener for alarm when present`() {
        val result = subject.parse(fullDumpResource)

        val batch = result.batches.first { it.id == "c64e97f" }
        val alarm = batch.alarms.first { it.id == "d564d4c" }

        assertNotNull(alarm.listener)
    }
}
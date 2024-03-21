package amaterek.util.log

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import java.util.logging.Level as JavaLevel
import java.util.logging.Logger as JavaLogger

class JvmLoggerTest {

    private lateinit var subject: Log.Logger
    private lateinit var javaLogger: JavaLogger

    private val testTag = "testTag"
    private val testMessage = "testMessage"
    private val testThrowable = Exception("testThrowable")

    @Before
    fun setUp() {
        mockkStatic("amaterek.util.log.Logger_jvmKt")
        javaLogger = mockk()
        every { getJavaLogger() } returns javaLogger
        subject = getDefaultLogger()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN verbose(String, String, Throwable) is called THEN android Log v is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.FINEST,
            loggerMethod = subject::verbose,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN verbose(String, String, null) is called THEN android Log v is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.FINEST,
            loggerMethod = subject::verbose,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN debug(String, String, Throwable) is called THEN android Log d is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.FINE,
            loggerMethod = subject::debug,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN debug(String, String, null) is called THEN android Log d is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.FINE,
            loggerMethod = subject::debug,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN info(String, String, Throwable) is called THEN android Log i is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.INFO,
            loggerMethod = subject::info,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN info(String, String, null) is called THEN android Log i is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.INFO,
            loggerMethod = subject::info,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, String, Throwable) is called THEN android Log w is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.WARNING,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, String, null) is called THEN android Log w is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.WARNING,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, empty, null) is called THEN android Log warning is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.WARNING,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN error(String, String, Throwable) is called THEN android Log e is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.SEVERE,
            loggerMethod = subject::error,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN error(String, String, null) is called THEN android Log e is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.SEVERE,
            loggerMethod = subject::error,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, String, Throwable) is called THEN android Log wtf is called`() {
        LogTestRuleTagMessageThrowable(
            level = JavaLevel.SEVERE,
            loggerMethod = subject::wtf,
            isWtf = true,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, String, null) is called THEN android Log wtf is called`() {
        LogTestRuleTagMessage(
            level = JavaLevel.SEVERE,
            loggerMethod = subject::wtf,
            isWtf = true,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, empty, Throwable) is called THEN android Log wtf is called`() {
        LogTestRuleTagThrowable(
            level = JavaLevel.SEVERE,
            loggerMethod = subject::wtf,
            isWtf = true,
        ).`assert that Log method is called when logger method is called`()
    }

    private data class LogTestRuleTagMessageThrowable(
        val level: JavaLevel,
        val loggerMethod: (String, String, Throwable?) -> Unit,
        val isWtf: Boolean = false,
    )

    private fun LogTestRuleTagMessageThrowable.`assert that Log method is called when logger method is called`() {
        val expectedTag = when (isWtf) {
            true -> "WTF-$testTag"
            else -> testTag
        }

        every { javaLogger.log(level, "$expectedTag: $testMessage", testThrowable) } just runs

        loggerMethod(testTag, testMessage, testThrowable)

        verify { javaLogger.log(level, "$expectedTag: $testMessage", testThrowable) }
    }

    private data class LogTestRuleTagMessage(
        val level: JavaLevel,
        val loggerMethod: (String, String, Throwable?) -> Unit,
        val isWtf: Boolean = false,
    )

    private fun LogTestRuleTagMessage.`assert that Log method is called when logger method is called`() {
        val expectedTag = when (isWtf) {
            true -> "WTF-$testTag"
            else -> testTag
        }

        every { javaLogger.log(level, "$expectedTag: $testMessage") } just runs

        loggerMethod(testTag, testMessage, null)

        verify { javaLogger.log(level, "$expectedTag: $testMessage") }
    }

    private data class LogTestRuleTagThrowable(
        val level: JavaLevel,
        val loggerMethod: (String, String, Throwable?) -> Unit,
        val isWtf: Boolean = false,
    )

    private fun LogTestRuleTagThrowable.`assert that Log method is called when logger method is called`() {
        val expectedTag = when (isWtf) {
            true -> "WTF-$testTag"
            else -> testTag
        }

        every { javaLogger.log(level, any(), testThrowable) } just runs

        loggerMethod(testTag, "", testThrowable)

        verify { javaLogger.log(level, expectedTag, testThrowable) }
    }
}
package amaterek.util.log

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import android.util.Log as AndroidLog

class AndroidLoggerTest {

    private lateinit var subject: Log.Logger

    private val testTag = "testTag"
    private val testMessage = "testMessage"
    private val testThrowable = Exception("testThrowable")

    @Before
    fun setUp() {
        mockkStatic(AndroidLog::class)

        subject = getDefaultLogger()
    }

    @After
    fun tearDown() {
        unmockkStatic(AndroidLog::class)
    }

    @Test
    fun `WHEN verbose(String, String, Throwable) is called THEN android Log v is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::v,
            loggerMethod = subject::verbose,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN verbose(String, String, null) is called THEN android Log v is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::v,
            loggerMethod = subject::verbose,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN debug(String, String, Throwable) is called THEN android Log d is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::d,
            loggerMethod = subject::debug,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN debug(String, String, null) is called THEN android Log d is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::d,
            loggerMethod = subject::debug,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN info(String, String, Throwable) is called THEN android Log i is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::i,
            loggerMethod = subject::info,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN info(String, String, null) is called THEN android Log i is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::i,
            loggerMethod = subject::info,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, String, Throwable) is called THEN android Log w is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::w,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, String, null) is called THEN android Log w is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::w,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN warning(String, empty, null) is called THEN android Log warning is called`() {
        LogTestRuleTagThrowable(
            logMethod = AndroidLog::w,
            loggerMethod = subject::warning,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN error(String, String, Throwable) is called THEN android Log e is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::e,
            loggerMethod = subject::error,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN error(String, String, null) is called THEN android Log e is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::e,
            loggerMethod = subject::error,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, String, Throwable) is called THEN android Log wtf is called`() {
        LogTestRuleTagMessageThrowable(
            logMethod = AndroidLog::wtf,
            loggerMethod = subject::wtf,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, String, null) is called THEN android Log wtf is called`() {
        LogTestRuleTagMessage(
            logMethod = AndroidLog::wtf,
            loggerMethod = subject::wtf,
        ).`assert that Log method is called when logger method is called`()
    }

    @Test
    fun `WHEN wtf(String, empty, Throwable) is called THEN android Log wtf is called`() {
        LogTestRuleTagThrowable(
            logMethod = AndroidLog::wtf,
            loggerMethod = subject::wtf,
        ).`assert that Log method is called when logger method is called`()
    }

    private data class LogTestRuleTagMessageThrowable(
        val logMethod: (String, String, Throwable?) -> Int,
        val loggerMethod: (String, String, Throwable?) -> Unit,
    )

    private fun LogTestRuleTagMessageThrowable.`assert that Log method is called when logger method is called`() {
        every { logMethod(testTag, testMessage, testThrowable) } returns 0

        loggerMethod(testTag, testMessage, testThrowable)

        verify { logMethod(testTag, testMessage, testThrowable) }
    }

    private data class LogTestRuleTagMessage(
        val logMethod: (String, String) -> Int,
        val loggerMethod: (String, String, Throwable?) -> Unit,
    )

    private fun LogTestRuleTagMessage.`assert that Log method is called when logger method is called`() {
        every { logMethod(testTag, testMessage) } returns 0

        loggerMethod(testTag, testMessage, null)

        verify { logMethod(testTag, testMessage) }
    }

    private data class LogTestRuleTagThrowable(
        val logMethod: (String?, Throwable) -> Int,
        val loggerMethod: (String, String, Throwable?) -> Unit,
    )

    private fun LogTestRuleTagThrowable.`assert that Log method is called when logger method is called`() {
        every { logMethod(testTag, testThrowable) } returns 0

        loggerMethod(testTag, "", testThrowable)

        verify { logMethod(testTag, testThrowable) }
    }
}
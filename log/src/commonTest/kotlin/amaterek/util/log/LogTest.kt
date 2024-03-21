package amaterek.util.log

import io.mockk.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class LogTest {

    private lateinit var mockedLogger: Log.Logger

    private val testTag = "testTag"
    private val testMessage = "testMessage"
    private val testThrowable = mockk<Throwable>()

    @BeforeTest
    fun setUp() {
        mockedLogger = mockk<Log.Logger>().also { Log.setLogger(it) }
    }

    @Test
    fun `WHEN 'v' is called THEN logger logs verbose for proper log level`() {
        val testRule = LogTestRule(
            logMethod = Log::v,
            loggerMethod = mockedLogger::verbose
        )
        with(testRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that non of logger method was called for`(level = Log.Level.DEBUG)
            `assert that non of logger method was called for`(level = Log.Level.INFO)
            `assert that non of logger method was called for`(level = Log.Level.WARNING)
            `assert that non of logger method was called for`(level = Log.Level.ERROR)
            `assert that non of logger method was called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    @Test
    fun `WHEN 'd' is called THEN logger logs debug for proper log level`() {
        val logTestRule = LogTestRule(
            logMethod = Log::d,
            loggerMethod = mockedLogger::debug
        )
        with(logTestRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that logger method is called for`(level = Log.Level.DEBUG)
            `assert that non of logger method was called for`(level = Log.Level.INFO)
            `assert that non of logger method was called for`(level = Log.Level.WARNING)
            `assert that non of logger method was called for`(level = Log.Level.ERROR)
            `assert that non of logger method was called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    @Test
    fun `WHEN 'i' is called THEN logger logs info for proper log level`() {
        val logTestRule = LogTestRule(
            logMethod = Log::i,
            loggerMethod = mockedLogger::info
        )
        with(logTestRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that logger method is called for`(level = Log.Level.DEBUG)
            `assert that logger method is called for`(level = Log.Level.INFO)
            `assert that non of logger method was called for`(level = Log.Level.WARNING)
            `assert that non of logger method was called for`(level = Log.Level.ERROR)
            `assert that non of logger method was called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    @Test
    fun `WHEN 'w' is called THEN logger logs warning for proper log level`() {
        val logTestRule = LogTestRule(
            logMethod = Log::w,
            loggerMethod = mockedLogger::warning
        )
        with(logTestRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that logger method is called for`(level = Log.Level.DEBUG)
            `assert that logger method is called for`(level = Log.Level.INFO)
            `assert that logger method is called for`(level = Log.Level.WARNING)
            `assert that non of logger method was called for`(level = Log.Level.ERROR)
            `assert that non of logger method was called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    @Test
    fun `WHEN 'e' is called THEN logger logs error for proper log level`() {
        val logTestRule = LogTestRule(
            logMethod = Log::e,
            loggerMethod = mockedLogger::error
        )
        with(logTestRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that logger method is called for`(level = Log.Level.DEBUG)
            `assert that logger method is called for`(level = Log.Level.INFO)
            `assert that logger method is called for`(level = Log.Level.WARNING)
            `assert that logger method is called for`(level = Log.Level.ERROR)
            `assert that non of logger method was called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    @Test
    fun `WHEN 'wtf' is called THEN logger logs wtf for proper log level`() {
        val logTestRule = LogTestRule(
            logMethod = Log::wtf,
            loggerMethod = mockedLogger::wtf
        )
        with(logTestRule) {
            `assert that logger method is called for`(level = Log.Level.VERBOSE)
            `assert that logger method is called for`(level = Log.Level.DEBUG)
            `assert that logger method is called for`(level = Log.Level.INFO)
            `assert that logger method is called for`(level = Log.Level.WARNING)
            `assert that logger method is called for`(level = Log.Level.ERROR)
            `assert that logger method is called for`(level = Log.Level.ASSERT)
            `assert that non of logger method was called for`(level = Log.Level.NONE)
        }
    }

    private data class LogTestRule(
        val logMethod: (String, String, Throwable?) -> Unit,
        val loggerMethod: (String, String, Throwable?) -> Unit,
    )

    private fun LogTestRule.`assert that logger method is called for`(level: Log.Level) {
        Log.setLevel(level)
        clearMocks(mockedLogger)
        every { loggerMethod(testTag, testMessage, testThrowable) } just runs

        logMethod(testTag, testMessage, testThrowable)

        verify(exactly = 1) { loggerMethod(testTag, testMessage, testThrowable) }
    }

    private fun LogTestRule.`assert that non of logger method was called for`(
        level: Log.Level,
    ) {
        Log.setLevel(level)
        clearMocks(mockedLogger)

        logMethod(testTag, testMessage, testThrowable)

        verify(exactly = 0) { mockedLogger wasNot called }
    }
}
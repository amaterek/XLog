package amaterek.util.log

import io.mockk.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class ConsoleLoggerTest() {

    private val testTag = "testTag"
    private val testMessage = "testMessage"
    private val testThrowable = Exception("testThrowable")

    private lateinit var subject: ConsoleLogger

    private val kotlinIoHClass = System.out

    @BeforeTest
    fun setUp() {
        subject = ConsoleLogger()
    }

    @Test
    fun `WHEN 'v' with tag and msg is called THEN logs verbose`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.verbose(testTag, testMessage)

        verify { println("VERBOSE: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'v' with tag and msg and throwable is called THEN logs verbose`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.verbose(testTag, testMessage, testThrowable)

        verify { println("VERBOSE: $testTag: $testMessage\n$testThrowable") }
    }

    @Test
    fun `WHEN 'd' with tag and msg is called THEN logs debug`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.debug(testTag, testMessage)

        verify { println("DEBUG: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'd' with tag and msg and throwable is called THEN logs debug`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.debug(testTag, testMessage, testThrowable)

        verify {
            println("DEBUG: $testTag: $testMessage\n$testThrowable")
        }
    }

    @Test
    fun `WHEN 'i' with tag and msg is called THEN logs info`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.info(testTag, testMessage)

        verify { println("INFO: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'i' with tag and msg and throwable is called THEN logs debug`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.info(testTag, testMessage, testThrowable)

        verify {
            println("INFO: $testTag: $testMessage\n$testThrowable")
        }
    }

    @Test
    fun `WHEN 'w' with tag and msg is called THEN logs warning`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.warning(testTag, testMessage)

        verify { println("WARN: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'w' with tag and msg and throwable is called THEN logs warning`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.warning(testTag, testMessage, testThrowable)

        verify {
            println("WARN: $testTag: $testMessage\n$testThrowable")
        }
    }

    @Test
    fun `WHEN 'e' with tag and msg is called THEN logs error`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.error(testTag, testMessage)

        verify { println("ERROR: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'e' with tag and msg and throwable is called THEN logs error`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.error(testTag, testMessage, testThrowable)

        verify {
            println("ERROR: $testTag: $testMessage\n$testThrowable")
        }
    }

    @Test
    fun `WHEN 'wtf' with tag and msg is called THEN logs warning`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.wtf(testTag, testMessage)

        verify { println("WTF: $testTag: $testMessage") }
    }

    @Test
    fun `WHEN 'wtf' with tag and msg and throwable is called THEN logs wtf`() = consoleOutputTest {
        every { println(any<String>()) } just runs

        subject.wtf(testTag, testMessage, testThrowable)

        verify {
            println("WTF: $testTag: $testMessage\n$testThrowable")
        }
    }

    private inline fun consoleOutputTest(test: () -> Unit) = mockkObject(kotlinIoHClass) {
        test()
    }
}
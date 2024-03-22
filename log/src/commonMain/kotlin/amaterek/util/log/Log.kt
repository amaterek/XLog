package amaterek.util.log

// For compatibility with Android's Log
object Log {

    @Suppress("MagicNumber")
    enum class Level(val value: Int) {
        VERBOSE(1),
        DEBUG(2),
        INFO(3),
        WARNING(4),
        ERROR(5),
        ASSERT(6),
        NONE(7),
    }

    fun setLogger(logger: Logger) {
        this.logger = logger
    }

    fun setLevel(level: Level) {
        this.level = level
    }

    fun v(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.VERBOSE.value) logger.verbose(tag, message, throwable)
    }

    fun d(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.DEBUG.value) logger.debug(tag, message, throwable)
    }

    fun i(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.INFO.value) logger.info(tag, message, throwable)
    }

    fun w(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.WARNING.value) logger.warning(tag, message, throwable)
    }

    fun w(tag: String, throwable: Throwable? = null) {
        if (level.value <= Level.WARNING.value) logger.warning(tag, "", throwable)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.ERROR.value) logger.error(tag, message, throwable)
    }

    fun wtf(tag: String, message: String, throwable: Throwable? = null) {
        if (level.value <= Level.ASSERT.value) logger.wtf(tag, message, throwable)
    }

    fun wtf(tag: String, throwable: Throwable? = null) {
        if (level.value <= Level.ASSERT.value) logger.wtf(tag, "", throwable)
    }

    interface Logger {

        fun verbose(tag: String, message: String, throwable: Throwable? = null)

        fun debug(tag: String, message: String, throwable: Throwable? = null)

        fun info(tag: String, message: String, throwable: Throwable? = null)

        fun warning(tag: String, message: String, throwable: Throwable? = null)

        fun error(tag: String, message: String, throwable: Throwable? = null)

        fun wtf(tag: String, message: String, throwable: Throwable? = null)
    }

    private lateinit var logger: Logger
    private var level: Level = Level.VERBOSE
}

expect fun getDefaultLogger(): Log.Logger
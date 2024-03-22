package amaterek.util.log

import java.util.logging.Level
import java.util.logging.Logger

private class JvmLogger : Log.Logger {

    private val logger: Logger = getJavaLogger()

    override fun verbose(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) logger.log(Level.FINEST, "$tag: $message", throwable) else
            logger.log(Level.FINEST, "$tag: $message")
    }

    override fun debug(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) logger.log(Level.FINE, "$tag: $message", throwable) else
            logger.log(Level.FINE, "$tag: $message")
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) logger.log(Level.INFO, "$tag: $message", throwable) else
            logger.log(Level.INFO, "$tag: $message")
    }

    override fun warning(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            if (message.isEmpty()) {
                logger.log(Level.WARNING, tag, throwable)
            } else {
                logger.log(Level.WARNING, "$tag: $message", throwable)
            }
        } else {
            logger.log(Level.WARNING, "$tag: $message")
        }
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) logger.log(Level.SEVERE, "$tag: $message", throwable) else
            logger.log(Level.SEVERE, "$tag: $message")
    }

    override fun wtf(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            if (message.isEmpty()) {
                logger.log(Level.SEVERE, "WTF-$tag", throwable)
            } else {
                logger.log(Level.SEVERE, "WTF-$tag: $message", throwable)
            }
        } else {
            logger.log(Level.SEVERE, "WTF-$tag: $message")
        }
    }
}

actual fun getDefaultLogger(): Log.Logger = JvmLogger()

// For testing purpose - How to mock Logger.getAnonymousLogger()?
internal fun getJavaLogger(): Logger = Logger.getAnonymousLogger()
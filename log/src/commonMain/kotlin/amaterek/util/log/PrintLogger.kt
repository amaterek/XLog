package amaterek.util.log

class PrintLogger : Log.Logger {

    override fun verbose(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("VERBOSE: $tag: $message\n$throwable") else println("VERBOSE: $tag: $message")
    }

    override fun debug(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("DEBUG: $tag: $message\n$throwable") else println("DEBUG: $tag: $message")
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("INFO: $tag: $message\n$throwable") else println("INFO: $tag: $message")
    }

    override fun warning(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("WARN: $tag: $message\n$throwable") else println("WARN: $tag: $message")
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("ERROR: $tag: $message\n$throwable") else println("ERROR: $tag: $message")
    }

    override fun wtf(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) println("WTF: $tag: $message\n$throwable") else println("WTF: $tag: $message")
    }
}
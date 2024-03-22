[![Release](https://jitpack.io/v/amaterek/XLog.svg)](https://jitpack.io/#amaterek/XLog)

# Kotlin multiplatform logging library
The library came out due to the extending projects to Kotlin Multiplatform.
The main principle is logging in the same way as in Android (android.util.Log).

## Supported platforms
* Android (supports android.util.Log)
* JVM (supports java.util.logging.Logger)
* iOS (supports only Kotlin's print. TODO: implemented according to iOS logging principles)
* Js (supports only Kotlin's print. TODO: implemented according to WEB logging principles)
* wasmJs (supports only Kotlin's print. TODO: implemented according to WEB logging principles)

## Examples

First add jitpack repository

```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

Then add this to your dependencies

```gradle
dependencies {
    implementation 'com.github.amaterek:XLog:v0.2'
}
```

### Initialize the Logger and set logging level
```kotlin
Log.setLogger(getDefaultLogger())
Log.setLevel(Logger.Level.DEBUG) // Optional, by default all levels are active
```

### Using the Log
```kotlin
// Verbose logs
Log.v("MyTag", "My message")
Log.v("MyTag", "My message", throwable)

// Debug logs
Log.d("MyTag", "My message")
Log.d("MyTag", "My message", throwable)

// Info logs
Log.i("MyTag", "My message")
Log.i("MyTag", "My message", throwable)

// Warning logs
Log.w("MyTag", "My message")
Log.w("MyTag", "My message", throwable)
Log.w("MyTag", throwable) // additional use case to log only stack trace

// Error logs
Log.e("MyTag", "My message")
Log.e("MyTag", "My message", throwable)

// WTF logs - logs situations that shouldn't happen
Log.wtf("MyTag", "My message")
Log.wtf("MyTag", "My message", throwable)
Log.wtf("MyTag", throwable) // additional use case to log only stack trace
```

### Custom loggers
Custom logger can be implemented by extending Log.Logger. It may be useful to log exceptions or other valuable information to the cloud.
```kotlin
class CustomLogger : Log.Logger {

    override fun verbose(tag: String, message: String, throwable: Throwable?) {
        ...
    }

    override fun debug(tag: String, message: String, throwable: Throwable?) {
        ...
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        ...
    }

    override fun warning(tag: String, message: String, throwable: Throwable?) {
        ...
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        ...
    }

    override fun wtf(tag: String, message: String, throwable: Throwable?) {
        ...
    }
}
```

### Log and ProGuard and R8
Add the following code to your ProGuard file to get rid of unwanted logs in the release builds (if ProGuard or R8 is enabled)
```
-assumenosideeffects class amaterek.util.Log {
    public void v(...);
    public void d(...);
}
```

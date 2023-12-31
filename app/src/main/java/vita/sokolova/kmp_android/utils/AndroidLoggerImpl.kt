package vita.sokolova.kmp_android.utils

import android.util.Log
import javax.inject.Inject

class AndroidLoggerImpl @Inject constructor(): Logger {

    override fun error(e: Throwable) {
        Log.e(ERROR_TAG, e.message + e.stackTraceToString())
    }

    companion object {
        private const val ERROR_TAG = "SearchWithPaginationTaskError"
    }
}
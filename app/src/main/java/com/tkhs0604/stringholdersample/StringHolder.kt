package com.tkhs0604.stringholdersample

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class StringHolder : Parcelable {
    abstract fun getString(context: Context): String

    @Parcelize
    data class Plain(val value: String) : StringHolder() {
        override fun getString(context: Context): String = value
    }

    @Parcelize
    class Resource(
        @StringRes private val resId: Int,
        private vararg val formatArgs: @RawValue Any
    ) : StringHolder() {
        override fun getString(context: Context): String {
            return if (formatArgs.isNotEmpty()) {
                context.getString(resId, *formatArgs)
            } else {
                context.getString(resId)
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Resource

            if (resId != other.resId) return false
            if (!formatArgs.contentEquals(other.formatArgs)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + formatArgs.contentHashCode()
            return result
        }
    }
}

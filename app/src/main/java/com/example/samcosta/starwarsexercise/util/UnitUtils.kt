package com.example.samcosta.starwarsexercise.util

object UnitUtils {
    fun convertCmToMeters(centiMeters: String): String {
        if (centiMeters.isBlank()) {
            return ""
        }

        return try {
            (centiMeters.toDouble() / 100).toString();
        } catch (ignored: NumberFormatException) {
            ""
        }
    }

}

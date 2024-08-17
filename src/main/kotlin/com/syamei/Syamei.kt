package com.syamei

object Syamei {
    private val corporateTypes: List<CorporateType> = loadCorporateTypes()

    fun containsCorporateType(companyName: String): Boolean {
        return corporateTypes.any { corporateType ->
            val fullMatch = "^${corporateType.full}|${corporateType.full}$".toRegex()
            val kanjiShortMatch = corporateType.kanjiShort?.let { "^$it|$it$".toRegex() }
            val kanaShortMatch = containsKanaShort(companyName, corporateType.kanaShort)
            val specialMatch = corporateType.special?.let { "^$it|$it$".toRegex() }

            fullMatch.containsMatchIn(companyName) ||
                kanjiShortMatch?.containsMatchIn(companyName) == true ||
                kanaShortMatch == true ||
                specialMatch?.containsMatchIn(companyName) == true
        }
    }

    private fun containsKanaShort(
        companyName: String,
        kanaShort: String,
    ): Boolean {
        val patterns =
            listOf(
                "^${kanaShort}\\)$",
                "^$kanaShort[^)]",
                "\\($kanaShort$",
            )
        return patterns.any { companyName.matches(Regex(it)) }
    }
}

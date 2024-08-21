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
                kanaShortMatch ||
                specialMatch?.containsMatchIn(companyName) == true
        }
    }

    fun removeCorporateType(companyName: String): String {
        val result = companyName

        for (corporateType in corporateTypes) {
            val fullMatch = "^${corporateType.full}|${corporateType.full}$".toRegex()
            val kanjiShortMatch = corporateType.kanjiShort?.let { "^$it|$it$".toRegex() }
            val kanaShortMatch = corporateType.kanaShort.let { "^$it）|（$it$".toRegex() }
            val specialMatch = corporateType.special?.let { "^$it|$it$".toRegex() }

            when {
                fullMatch.containsMatchIn(companyName) -> return result.replace(fullMatch, "")
                kanjiShortMatch?.containsMatchIn(companyName) == true -> return result.replace(kanjiShortMatch, "")
                kanaShortMatch.containsMatchIn(companyName) -> return result.replace(kanaShortMatch, "")
                specialMatch?.containsMatchIn(companyName) == true -> return result.replace(specialMatch, "")
            }
        }
        return result
    }

    private fun containsKanaShort(
        companyName: String,
        kanaShort: String,
    ): Boolean {
        val patterns =
            listOf(
                "^$kanaShort）.*$",
                ".*（$kanaShort$",
            )
        return patterns.any { companyName.matches(Regex(it)) }
    }
}

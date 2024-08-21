package com.syamei

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.DefaultAsserter.assertEquals

class SyameiTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "kotlin        | false",
            "株式会社kotlin | true",
            "kotlin株式会社 | true",
            "kot株式会社lin | false",
            "（有）kotlin   | true",
            "kotlin（有）   | true",
            "ko（有）tlin   | false",
            "シヤ）kotlin   | true",
            "シャ）kotlin   | true",
            "kotlin（シヤ   | true",
            "kotlin（シャ   | true",
            "kot（カ）lin   | false",
            "カ（kotlin     | false",
            "㈴kotlin      | true",
            "kotlin㈴      | true",
            "kot㈴lin      | false",
        ],
        delimiter = '|',
    )
    fun returnsBoolContainsCorporateTypeOrNot(
        fullName: String,
        expected: Boolean,
    ) {
        val actual = Syamei.containsCorporateType(fullName)
        assertEquals("fullName is $fullName", expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "kotlin        | kotlin",
            "株式会社kotlin | kotlin",
            "kotlin株式会社 | kotlin",
            "kot株式会社lin | kot株式会社lin",
            "（有）kotlin   | kotlin",
            "kotlin（有）   | kotlin",
            "ko（有）tlin   | ko（有）tlin",
            "シヤ）kotlin   | kotlin",
            "シャ）kotlin   | kotlin",
            "kotlin（シヤ   | kotlin",
            "kotlin（シャ   | kotlin",
            "kot（カ）lin   | kot（カ）lin",
            "カ（kotlin     | カ（kotlin",
            "㈴kotlin      | kotlin",
            "kotlin㈴      | kotlin",
            "kot㈴lin      | kot㈴lin",
        ],
        delimiter = '|',
    )
    fun returnsCompanyNameWithoutCorporateType(
        fullName: String,
        expected: String,
    ) {
        val actual = Syamei.removeCorporateType(fullName)
        assertEquals("fullName is $fullName", expected, actual)
    }
}

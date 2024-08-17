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
            "カ）kotlin     | true",
            "kotlin（カ     | true",
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
}

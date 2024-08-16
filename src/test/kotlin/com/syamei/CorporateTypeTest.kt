package com.syamei

import kotlin.test.Test
import kotlin.test.assertEquals

class CorporateTypeTest {
    @Test
    fun `returns correct CorporateType count`() {
        val corporateTypes = loadCorporateTypes()
        assertEquals(54, corporateTypes.size)
    }
}

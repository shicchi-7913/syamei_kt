package com.syamei

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class SyameiTest {
    @Test
    fun returnFullName() {
        assertEquals("hoge", Syamei("hoge").withoutCorporateType())
    }
}

package com.example.hiddencalculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val reg = "^\\d+$".toRegex()
        assertTrue("wrong", reg.matches("111"))
        assertTrue("wrong", reg.matches("111"))
    }
}
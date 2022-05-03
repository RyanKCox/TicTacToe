package com.revature.tictactoe.viewmodel

import junit.framework.TestCase
import org.junit.Test

class LoginViewModelTest : TestCase() {
    private val loginVM = LoginViewModel()

    @Test
    fun testUsernameCheck() {

        var sUsername = "Bobby"

        assertTrue(loginVM.usernameCheck(sUsername))


    }

    @Test
    fun testPasswordCheck() {

        val sPassword = "12345678"

        assertTrue(loginVM.passwordCheck(sPassword))

    }
}
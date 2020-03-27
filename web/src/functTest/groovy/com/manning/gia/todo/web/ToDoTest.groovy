package com.manning.gia.todo.web

import geb.*
import geb.junit4.*
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.Keys

@RunWith(JUnit4)
class ToDoTest extends GebReportingTest {
    @Test
    void theToDoHomepage() {
        to ToDoHomepage

        $("form").name = 'local end to end test'
        $("form").name << Keys.ENTER

        waitFor { at ToDoInsert }
    }
}
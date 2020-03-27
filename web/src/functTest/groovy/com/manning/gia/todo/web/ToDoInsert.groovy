package com.manning.gia.todo.web

import geb.*

class ToDoInsert extends Page {
    static url = "http://localhost:8081/todo/insert"
    static at = { title == "To Do application" }
}
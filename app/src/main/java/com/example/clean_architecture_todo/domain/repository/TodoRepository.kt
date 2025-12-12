package com.example.clean_architecture_todo.domain.repository

import com.example.clean_architecture_todo.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}

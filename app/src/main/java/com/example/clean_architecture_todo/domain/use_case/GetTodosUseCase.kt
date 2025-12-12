package com.example.clean_architecture_todo.domain.use_case

import com.example.clean_architecture_todo.domain.model.TodoItem
import com.example.clean_architecture_todo.domain.repository.TodoRepository

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> = repository.getTodos()
}

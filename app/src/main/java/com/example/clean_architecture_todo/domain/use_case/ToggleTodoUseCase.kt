package com.example.clean_architecture_todo.domain.use_case

import com.example.clean_architecture_todo.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        repository.toggleTodo(id)
    }
}

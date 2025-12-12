package com.example.clean_architecture_todo

import com.example.clean_architecture_todo.domain.model.TodoItem
import com.example.clean_architecture_todo.domain.repository.TodoRepository
import com.example.clean_architecture_todo.domain.use_case.ToggleTodoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class ToggleTodoUseCaseTest {

    private val todos = mutableListOf(
        TodoItem(1, "A", "desc A", false),
    )

    private val repository = object : TodoRepository {
        override suspend fun getTodos(): List<TodoItem> = todos
        override suspend fun toggleTodo(id: Int) {
            todos.replaceAll {
                if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it
            }
        }
    }

    private val useCase = ToggleTodoUseCase(repository)

    @Test
    fun `toggleTodo flips isCompleted`() = runBlocking {
        useCase(1)
        val todo = repository.getTodos().first()
        assertTrue(todo.isCompleted)
    }
}

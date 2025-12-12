package com.example.clean_architecture_todo

import com.example.clean_architecture_todo.domain.model.TodoItem
import com.example.clean_architecture_todo.domain.repository.TodoRepository
import com.example.clean_architecture_todo.domain.use_case.GetTodosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTodosUseCaseTest {

    private val mockTodos = listOf(
        TodoItem(1, "A", "desc A", false),
        TodoItem(2, "B", "desc B", true),
        TodoItem(3, "C", "desc C", false)
    )

    private val repository = object : TodoRepository {
        override suspend fun getTodos(): List<TodoItem> = mockTodos
        override suspend fun toggleTodo(id: Int) {}
    }

    private val useCase = GetTodosUseCase(repository)

    @Test
    fun `GetTodosUseCase returns 3 todos`() = runBlocking {
        val todos = useCase()
        assertEquals(3, todos.size)
    }
}

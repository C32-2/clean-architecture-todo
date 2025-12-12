package com.example.clean_architecture_todo

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.clean_architecture_todo.domain.model.TodoItem
import com.example.clean_architecture_todo.domain.repository.TodoRepository
import com.example.clean_architecture_todo.domain.use_case.GetTodosUseCase
import com.example.clean_architecture_todo.domain.use_case.ToggleTodoUseCase
import com.example.clean_architecture_todo.navigation.NavGraph
import com.example.clean_architecture_todo.presentation.ui.screen.TodoListScreen
import com.example.clean_architecture_todo.presentation.viewmodel.TodoViewModel
import org.junit.Rule
import org.junit.Test

class TodoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val todos = listOf(
        TodoItem(1, "A", "desc A", false),
        TodoItem(2, "B", "desc B", true),
        TodoItem(3, "C", "desc C", false)
    )

    @Test
    fun allTodosDisplayed() {
        val viewModel = TodoViewModel(
            GetTodosUseCase(object : TodoRepository {
                override suspend fun getTodos() = todos
                override suspend fun toggleTodo(id: Int) {}
            }),
            ToggleTodoUseCase(object : TodoRepository {
                override suspend fun getTodos() = todos
                override suspend fun toggleTodo(id: Int) {}
            })
        )

        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel, onTodoClick = {})
        }

        todos.forEach { todo ->
            composeTestRule.onNodeWithText(todo.title).assertIsDisplayed()
        }
    }

    @Test
    fun checkboxTogglesStatus() {
        val todos = mutableListOf(TodoItem(1, "Купить молоко", "2 литра", false))
        val repository = object : TodoRepository {
            override suspend fun getTodos() = todos
            override suspend fun toggleTodo(id: Int) {
                todos.replaceAll { if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it }
            }
        }

        val viewModel = TodoViewModel(GetTodosUseCase(repository), ToggleTodoUseCase(repository))

        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel, onTodoClick = {})
        }

        composeTestRule.onAllNodes(isToggleable()).onFirst().performClick()

        composeTestRule.runOnIdle {
            assert(todos.first().isCompleted)
        }
    }

    @Test
    fun navigationListToDetailAndBack() {
        val todos = listOf(TodoItem(1, "Купить молоко", "2 литра", false))
        val viewModel = TodoViewModel(
            GetTodosUseCase(object : TodoRepository {
                override suspend fun getTodos() = todos
                override suspend fun toggleTodo(id: Int) {}
            }),
            ToggleTodoUseCase(object : TodoRepository {
                override suspend fun getTodos() = todos
                override suspend fun toggleTodo(id: Int) {}
            })
        )

        composeTestRule.setContent {
            NavGraph(viewModel)
        }

        composeTestRule.onNodeWithText("Купить молоко").performClick()

        composeTestRule.onNodeWithText("2 литра").assertIsDisplayed()

        composeTestRule.onNodeWithText("Назад").performClick()

        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
    }

}

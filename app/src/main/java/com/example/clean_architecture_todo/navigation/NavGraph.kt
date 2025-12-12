package com.example.clean_architecture_todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clean_architecture_todo.presentation.ui.screen.TodoDetailScreen
import com.example.clean_architecture_todo.presentation.ui.screen.TodoListScreen
import com.example.clean_architecture_todo.presentation.viewmodel.TodoViewModel

@Composable
fun NavGraph(viewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { id -> navController.navigate("detail/$id") }
            )
        }

        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: return@composable
            val todo = viewModel.todos.value.first { it.id == id }

            TodoDetailScreen(todo) {
                navController.popBackStack()
            }
        }
    }
}

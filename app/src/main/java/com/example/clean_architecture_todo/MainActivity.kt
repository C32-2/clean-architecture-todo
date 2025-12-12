package com.example.clean_architecture_todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.clean_architecture_todo.data.local.TodoJsonDataSource
import com.example.clean_architecture_todo.data.repository.TodoRepositoryImpl
import com.example.clean_architecture_todo.domain.use_case.GetTodosUseCase
import com.example.clean_architecture_todo.domain.use_case.ToggleTodoUseCase
import com.example.clean_architecture_todo.navigation.NavGraph
import com.example.clean_architecture_todo.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = TodoJsonDataSource(this)
        val repository = TodoRepositoryImpl(dataSource)

        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)

        val viewModel = TodoViewModel(getTodosUseCase, toggleTodoUseCase)

        setContent {
            NavGraph(viewModel)
        }
    }
}
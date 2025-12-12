package com.example.clean_architecture_todo.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clean_architecture_todo.presentation.viewmodel.TodoViewModel


@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onTodoClick: (Int) -> Unit
) {
    val todos by viewModel.todos.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todos) { todo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTodoClick(todo.id) }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = todo.isCompleted,
                    onCheckedChange = { viewModel.toggleTodo(todo.id) }
                )
                Spacer(Modifier.width(8.dp))
                Text(todo.title, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

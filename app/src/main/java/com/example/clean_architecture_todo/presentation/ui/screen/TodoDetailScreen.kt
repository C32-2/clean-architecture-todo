package com.example.clean_architecture_todo.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clean_architecture_todo.domain.model.TodoItem

@Composable
fun TodoDetailScreen(todo: TodoItem, onBack: () -> Unit) {
    Column(modifier = Modifier
        .padding(top = 64.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(todo.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text(todo.description)
        Spacer(Modifier.height(8.dp))
        Text("Статус: ${if (todo.isCompleted) "Выполнено" else "Не выполнено"}")

        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Назад") }
    }
}

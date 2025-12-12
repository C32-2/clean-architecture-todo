package com.example.clean_architecture_todo.data.repository

import com.example.clean_architecture_todo.data.local.TodoJsonDataSource
import com.example.clean_architecture_todo.domain.model.TodoItem
import com.example.clean_architecture_todo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: TodoJsonDataSource
) : TodoRepository {

    private var cachedTodos: MutableList<TodoItem> = mutableListOf()

    override suspend fun getTodos(): List<TodoItem> {
        if (cachedTodos.isEmpty()) {
            cachedTodos = dataSource.getTodos()
                .map { dto ->
                    TodoItem(
                        id = dto.id,
                        title = dto.title,
                        description = dto.description,
                        isCompleted = dto.isCompleted
                    )
                }
                .toMutableList()
        }
        return cachedTodos
    }

    override suspend fun toggleTodo(id: Int) {
        cachedTodos = cachedTodos.map { todo ->
            if (todo.id == id) todo.copy(isCompleted = !todo.isCompleted)
            else todo
        }.toMutableList()
    }
}

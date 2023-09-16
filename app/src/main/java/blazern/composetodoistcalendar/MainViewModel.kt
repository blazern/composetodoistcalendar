package blazern.composetodoistcalendar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import blazern.todoist.Task
import blazern.todoist.TodoistApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoistApi: TodoistApi,
) : ViewModel() {
    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    val tasks: State<List<Task>> = _tasks

    init {
        viewModelScope.launch {
            val tasks = todoistApi.tasks()
            _tasks.value = tasks
        }
    }
}

package blazern.todoist

import retrofit2.http.GET

interface TodoistApi {
    @GET("/rest/v2/tasks")
    suspend fun tasks(): List<Task>
}

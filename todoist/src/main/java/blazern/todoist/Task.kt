package blazern.todoist

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("project_id")
    val projectId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("labels")
    val labels: List<String>,
)

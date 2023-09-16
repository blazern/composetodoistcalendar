package blazern.composetodoistcalendar

import blazern.todoist.TodoistModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TodoistModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}

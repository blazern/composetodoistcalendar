package blazern.todoist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class TodoistModule {
    @Provides
    @Singleton
    fun provideTodoistApi(
        retrofit: Retrofit,
    ): TodoistApi {
        return retrofit.create(TodoistApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://api.todoist.com")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("auth")
    internal fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.todoistToken}")
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        @Named("auth")
        authInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}

package com.omongole.fred.notesapp.util


sealed class Resource<out T> {
    data class Success<out T>( val data: T ) : Resource<T>()
    data class Failure(val errorMessage: String ) : Resource<Nothing>()
    object Loading: Resource<Nothing>()
}

package com.rghapp.tokencasestudy.data.tools

data class Resource<out T>(val status: Status, val data: T?, val error: Error?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        
        fun <T> error(error: Error, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }
        
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
        
        fun <T> idle(data: T?): Resource<T> {
            return Resource(Status.IDLE, data, null)
        }
        
    }
}

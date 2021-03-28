package com.rghapp.tokencasestudy.data.network.apiresponse

import com.rghapp.tokencasestudy.data.tools.Error
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            val errorMessage = error.message ?: "unknown error"
            return when(error){
                is IOException -> ApiErrorResponse(Error(Error.NETWORK_ERROR,errorMessage))
                is HttpException -> ApiErrorResponse(Error(Error.SERVER_ERROR,errorMessage))
                else -> ApiErrorResponse(Error(Error.UNKNOWN_REMOTE_ERROR,errorMessage))
            }
        }


        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val code = response.code()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(Error(code,errorMsg))
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val error: Error) : ApiResponse<T>()

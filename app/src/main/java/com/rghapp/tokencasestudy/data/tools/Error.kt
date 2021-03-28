package com.rghapp.tokencasestudy.data.tools

 class Error(val code: Int = UNKNOWN_REMOTE_ERROR, val description: String? = "") {
    companion object{
        const val SERVER_ERROR = -1
        const val NETWORK_ERROR = -2
        const val UNKNOWN_REMOTE_ERROR = -3
        const val BAD_REQUEST_ERROR = 400
        const val REMOTE_FORBIDDEN_ERROR = 403
        const val REMOTE_UNAUTHORIZED_ERROR = 401
        const val INVALID_PARAMETERS_ERROR = 422
        const val RESPONSE_EMPTY = 204
    }
}



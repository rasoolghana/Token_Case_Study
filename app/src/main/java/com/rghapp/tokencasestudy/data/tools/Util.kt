package com.rghapp.tokencasestudy.data.tools


import com.rghapp.tokencasestudy.data.network.apiresponse.ApiEmptyResponse
import com.rghapp.tokencasestudy.data.network.apiresponse.ApiErrorResponse
import com.rghapp.tokencasestudy.data.network.apiresponse.ApiResponse
import com.rghapp.tokencasestudy.data.network.apiresponse.ApiSuccessResponse

/**
 * Created by Rasool Ghana on 1/27/21.
 * Email : Rasool.ghana@gmail.com
 */

fun <ApiType, ResourceType> getResourceFromApiResponse(
    apiResponse: ApiResponse<ApiType>,
    mapApiTypeToResourceType: (ApiType) -> ResourceType
): Resource<ResourceType> {
    return when (apiResponse) {
        is ApiEmptyResponse -> Resource.success(null)
        is ApiSuccessResponse ->
            Resource.success(mapApiTypeToResourceType(apiResponse.body))
        is ApiErrorResponse -> Resource.error(apiResponse.error, null)
    }
}
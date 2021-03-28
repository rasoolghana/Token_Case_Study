package com.rghapp.tokencasestudy.data.network.services

import com.rghapp.tokencasestudy.data.model.remote.PaymentRequestBody
import com.rghapp.tokencasestudy.data.model.remote.PaymentResponse
import com.rghapp.tokencasestudy.data.model.remote.QrRequestBody
import com.rghapp.tokencasestudy.data.model.remote.QrRequestResponse
import com.rghapp.tokencasestudy.data.network.ApiEndPoints
import com.rghapp.tokencasestudy.data.network.apiresponse.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
interface PaymentService {

    @POST(ApiEndPoints.QR_FOR_SALE)
    suspend fun requestQr(
        @Body body: QrRequestBody
    ): ApiResponse<QrRequestResponse>

    @POST(ApiEndPoints.PAYMENT)
    suspend fun submitPayment(
            @Body body: PaymentRequestBody
    ): ApiResponse<PaymentResponse>

}
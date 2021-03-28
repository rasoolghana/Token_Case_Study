package com.rghapp.tokencasestudy.data.repository

import com.rghapp.tokencasestudy.data.model.local.PaymentInfo
import com.rghapp.tokencasestudy.data.model.remote.PaymentRequestBody
import com.rghapp.tokencasestudy.data.model.remote.PaymentResponse
import com.rghapp.tokencasestudy.data.model.remote.QrRequestBody
import com.rghapp.tokencasestudy.data.model.remote.QrRequestResponse
import com.rghapp.tokencasestudy.data.network.services.PaymentService
import com.rghapp.tokencasestudy.data.serviceinterfaces.PaymentRepository
import com.rghapp.tokencasestudy.data.tools.Resource
import com.rghapp.tokencasestudy.data.tools.getResourceFromApiResponse
import javax.inject.Inject

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
class PaymentRepositoryImpl @Inject constructor(
    private val paymentService: PaymentService
): PaymentRepository {

    override suspend fun requestQr(totalReceiptAmount: Int): Resource<QrRequestResponse> {
        val requestBody = QrRequestBody(totalReceiptAmount)
        val response = paymentService.requestQr(requestBody)
        return getResourceFromApiResponse(response) {
            it
        }
    }

    override suspend fun submitPayment(qrData: String, paymentInfoList: List<PaymentInfo>): Resource<PaymentResponse> {
        val requestBody = PaymentRequestBody(
            1000,
            "success",
            "beko Campaign/n2018",
            "beko Campaign Merchant/n2018",
            paymentInfoList,qrData
        )

        val response = paymentService.submitPayment(requestBody)
        return getResourceFromApiResponse(response){
            it
        }
    }

}
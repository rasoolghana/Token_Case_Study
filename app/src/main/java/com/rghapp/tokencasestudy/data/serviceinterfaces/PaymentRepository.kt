package com.rghapp.tokencasestudy.data.serviceinterfaces

import com.rghapp.tokencasestudy.data.model.local.PaymentInfo
import com.rghapp.tokencasestudy.data.model.remote.PaymentResponse
import com.rghapp.tokencasestudy.data.model.remote.QrRequestResponse
import com.rghapp.tokencasestudy.data.tools.Resource

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
interface PaymentRepository {

    suspend fun requestQr(
            totalReceiptAmount: Int
    ): Resource<QrRequestResponse>

    suspend fun submitPayment(
            qrData: String,
            paymentInfoList: List<PaymentInfo>
    ): Resource<PaymentResponse>
}
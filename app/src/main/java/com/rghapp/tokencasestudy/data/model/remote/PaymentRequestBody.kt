package com.rghapp.tokencasestudy.data.model.remote

import com.google.gson.annotations.SerializedName
import com.rghapp.tokencasestudy.data.model.local.PaymentInfo

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
data class PaymentRequestBody(
        @SerializedName("returnCode")
        val returnCode: Int,
        @SerializedName("returnDesc")
        val returnDesc: String,
        @SerializedName("receiptMsgCustomer")
        val receiptMessageCustomer: String,
        @SerializedName("receiptMsgMerchant")
        val receiptMessageMerchant: String,
        @SerializedName("paymentInfoList")
        val paymentInfoList: List<PaymentInfo>,
        @SerializedName("QRdata")
        val QrData: String
)

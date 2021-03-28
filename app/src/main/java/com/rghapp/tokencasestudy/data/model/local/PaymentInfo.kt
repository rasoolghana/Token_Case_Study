package com.rghapp.tokencasestudy.data.model.local

import com.google.gson.annotations.SerializedName

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
data class PaymentInfo(
    @SerializedName("paymentProcessorID")
    val paymentProcessorId: Int,
    @SerializedName("paymentActionList")
    val paymentActionList: List<PaymentAction>
)

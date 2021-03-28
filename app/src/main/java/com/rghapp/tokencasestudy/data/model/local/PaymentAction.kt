package com.rghapp.tokencasestudy.data.model.local

import com.google.gson.annotations.SerializedName

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
data class PaymentAction(
        @SerializedName("paymentType")
        val paymentType: Int,
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("currencyID")
        val currencyId: Int,
        @SerializedName("vatRate")
        val vatRate: Int
)

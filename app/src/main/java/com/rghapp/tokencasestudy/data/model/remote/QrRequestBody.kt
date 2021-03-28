package com.rghapp.tokencasestudy.data.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
data class QrRequestBody(
        @SerializedName("totalReceiptAmount")
        val totalReceiptAmount: Int
)

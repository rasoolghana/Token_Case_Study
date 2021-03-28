package com.rghapp.tokencasestudy.data.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
data class PaymentResponse (
    @SerializedName("returnCode")
    val returnCode: Int,
    @SerializedName("returnDesc")
    val returnDesc: String,
    @SerializedName("posID")
    val posId: String,
    @SerializedName("sessionID")
    val sessionId: String,
    @SerializedName("applicationID")
    val applicationId: String
)
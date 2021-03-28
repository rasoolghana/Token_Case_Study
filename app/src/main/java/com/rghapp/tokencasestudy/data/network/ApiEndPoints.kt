package com.rghapp.tokencasestudy.data.network

import com.rghapp.tokencasestudy.data.network.NetworkConfig.BASE_URL


object ApiEndPoints {
    const val QR_FOR_SALE = "${BASE_URL}get_qr_sale"
    const val PAYMENT = "${BASE_URL}payment"
}
package com.rghapp.tokencasestudy.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rghapp.tokencasestudy.data.model.local.PaymentAction
import com.rghapp.tokencasestudy.data.model.local.PaymentInfo
import com.rghapp.tokencasestudy.data.model.remote.QrRequestResponse
import com.rghapp.tokencasestudy.data.serviceinterfaces.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val paymentRepo: PaymentRepository
) : ViewModel() {

    companion object {
        const val AMOUNT = 100
    }

    private val qrData = MutableLiveData<QrRequestResponse>(null)
    val paymentStatus = MutableLiveData(PaymentStatus.None)
    val qrDataStatus = MutableLiveData(QrDataStatus.None)

    fun getQrData(){
        viewModelScope.launch(Dispatchers.IO){
            val qrResponse = paymentRepo.requestQr(AMOUNT)
            if (qrResponse.data != null){
                qrResponse.data.let {
                    qrData.postValue(it)
                    qrDataStatus.postValue(QrDataStatus.Received)
                }
            }else {
                qrDataStatus.postValue(QrDataStatus.Failed)
            }
        }
    }

    fun submitPayment(){
        if (qrData.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val response =
                    paymentRepo.submitPayment(qrData.value!!.QrData, generateDummyPaymentInfo())
                if (response.data != null){
                    paymentStatus.postValue(PaymentStatus.Successful)
                }else{
                    paymentStatus.postValue(PaymentStatus.Failed)
                }
            }
        }else{
            paymentStatus.postValue(PaymentStatus.None)
        }
    }

    private fun generateDummyPaymentInfo(): List<PaymentInfo>{
        val paymentAction = PaymentAction(
                3,
                AMOUNT,
                949,
                800
        )
        val paymentInfo = PaymentInfo(
                67,
                listOf(paymentAction)
        )
        return listOf(paymentInfo)
    }

}
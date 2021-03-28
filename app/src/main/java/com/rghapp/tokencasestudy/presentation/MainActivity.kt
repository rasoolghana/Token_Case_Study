package com.rghapp.tokencasestudy.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rghapp.tokencasestudy.R
import com.rghapp.tokencasestudy.databinding.ActivityMainBinding
import com.rghapp.tokencasestudy.isNetworkAvailable
import com.rghapp.tokencasestudy.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()

        bindObservers()

    }

    private fun bindObservers(){
        observe(viewModel.qrDataStatus, ::onQrCodeDataAvailable)
        observe(viewModel.paymentStatus,::onPaymentStatusChanged)
    }

    private fun initViews(){
        binding.apply {
            paymentButton.setOnClickListener(this@MainActivity)
            qrDataButton.setOnClickListener(this@MainActivity)
        }
    }

    private fun onQrCodeDataAvailable(qrDataStatus: QrDataStatus){
        binding.progressBar.visibility = View.GONE
        if (qrDataStatus == QrDataStatus.None || qrDataStatus == QrDataStatus.Failed){
            togglePaymentButton(false)
            if (qrDataStatus == QrDataStatus.Failed){
                showSnackBarMessage(getString(R.string.qr_data_failed))
            }
        }else{
            showSnackBarMessage(getString(R.string.qr_data_success))
            togglePaymentButton(true)
        }
    }

    private fun onPaymentStatusChanged(paymentStatus: PaymentStatus){
        binding.progressBar.visibility = View.GONE
        when (paymentStatus) {
            PaymentStatus.None -> {
                togglePaymentButton(false)
            }
            PaymentStatus.Failed -> {
                showSnackBarMessage(getString(R.string.payment_failed_message))
            }
            PaymentStatus.Successful -> {
                showSnackBarMessage(getString(R.string.payment_success_message))
            }
        }
    }

    private fun doActionIfNetworkAvailable(action: () -> Unit) {
        if (isNetworkAvailable()) {
            action()
            binding.progressBar.visibility = View.VISIBLE
        } else {
            showSnackBarMessage(getString(R.string.no_internet_connection))
        }
    }

    private fun showSnackBarMessage(message: String){
        Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
    }

    private fun togglePaymentButton(enable: Boolean){
        binding.paymentButton.apply {
            isEnabled = enable
            isClickable = enable
        }
    }

    override fun onClick(v: View) {
        doActionIfNetworkAvailable {
            if (v.id == R.id.qr_data_button){
                viewModel.getQrData()
            }else if (v.id == R.id.payment_button){
                viewModel.submitPayment()
            }
        }
    }
}
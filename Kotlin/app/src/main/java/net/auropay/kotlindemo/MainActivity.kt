package net.auropay.kotlindemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.auropay.kotlindemo.databinding.ActivityMainBinding
import net.auropay.payments.AuroPay
import net.auropay.payments.domain.enums.Country
import net.auropay.payments.domain.service.CustomerProfile
import net.auropay.payments.domain.service.PaymentResultListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializations()
    }

    private fun initializations() {
        binding.buttonPay.setOnClickListener {

            // Get AuroPay builder.
            val auroPay = AuroPay.Initialize(
                merchantId = "Your merchant id",
                accessKey = "Your access key",
                secretKey = "Your secret key",
                getCustomerProfile()
            )
                .autoContrast(true)
                .country(Country.IN)
                .showReceipt(true)
                .build()

            // Initiate payment request.
            auroPay.doPayment(
                context = this,
                amount = 1500,
                onPaymentResultListener = object : PaymentResultListener {
                    override fun onSuccess(orderId: String, transactionStatus: Int, transactionId: String) {
                        // Handle payment success.
                        Toast.makeText(this@MainActivity, "Success: $transactionId", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(message: String) {
                        // Handle payment failure.
                        Toast.makeText(this@MainActivity, "Failed: $message", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun getCustomerProfile() = CustomerProfile(
        "Some Title",
        binding.etFirstName.text.toString(),
        "",
        binding.etLastName.text.toString(),
        binding.etPhone.text.toString(),
        binding.etEmail.text.toString(),
    )
}
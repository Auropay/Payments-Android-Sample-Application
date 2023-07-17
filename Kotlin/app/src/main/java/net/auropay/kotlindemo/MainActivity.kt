package net.auropay.kotlindemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.auropay.kotlindemo.databinding.ActivityMainBinding
import net.auropay.payments.AuroPay
import net.auropay.payments.domain.enums.Country
import net.auropay.payments.domain.service.CustomerProfile
import net.auropay.payments.domain.service.EventListener
import net.auropay.payments.domain.service.PaymentResultListener
import net.auropay.payments.domain.service.Theme


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

            // Customize the AuroPay UI colors to match the hosting application theme.
            val theme = Theme(
                "#236C6C",
                "#FFFFFF",
                "#FFFFFF",
                "#FAAB03"
            )

            // Additional event listener.
            val eventListener = object : EventListener {
                override fun onEvent(eventId: Int, eventDesc: String) {
                    Log.d("AuroPay Event", "$eventId - $eventDesc")
                }
            }

            val customerProfile = getCustomerProfile()

            if (customerProfile != null) {
                // Initialize and get the AuroPay instance using builder.
                val auroPay = AuroPay.Initialize()
                    .subDomainId("Your subDomainId")
                    .accessKey("Your accessKey")
                    .secretKey("Your secretKey")
                    .customerProfile(customerProfile)
                    .country(Country.IN)
                    .theme(theme)
                    .addEventListener(eventListener)
                    .build()


                // Initiate payment request.
                auroPay.doPayment(
                    this,
                    binding.etAmount.text.toString().toDouble(),
                    askCustomerDetails = false,
                    // Consume the payment request result.
                    onPaymentResultListener = object : PaymentResultListener {
                        override fun onSuccess(orderId: String, transactionStatus: Int, transactionId: String) {
                            // Handle payment success result.
                            Toast.makeText(this@MainActivity, "Success: $transactionId", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(message: String) {
                            // Handle payment failure result.
                            Toast.makeText(this@MainActivity, "Failed: $message", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }

    // Create customer profile model.
    private fun getCustomerProfile(): CustomerProfile? {
        with(binding) {
            if (etAmount.text.toString().isBlank()) {
                Toast.makeText(this@MainActivity, "Amount should not be empty", Toast.LENGTH_SHORT).show()
                return null
            } else if (etAmount.text.toString().toDouble() <= 0.0) {
                Toast.makeText(this@MainActivity, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return null
            } else if (etFirstName.text.toString().isBlank()) {
                Toast.makeText(this@MainActivity, "First name should not be empty", Toast.LENGTH_SHORT).show()
                return null
            } else if (etLastName.text.toString().isBlank()) {
                Toast.makeText(this@MainActivity, "Last name should not be empty", Toast.LENGTH_SHORT).show()
                return null
            } else if (etPhone.text.toString().isBlank()) {
                Toast.makeText(this@MainActivity, "Phone number should not be empty", Toast.LENGTH_SHORT).show()
                return null
            } else if (etEmail.text.toString().isBlank()) {
                Toast.makeText(this@MainActivity, "Email should not be empty", Toast.LENGTH_SHORT).show()
                return null
            }
            return CustomerProfile(
                "Title",
                binding.etFirstName.text.toString(),
                "",
                binding.etLastName.text.toString(),
                binding.etPhone.text.toString(),
                binding.etEmail.text.toString(),
            )
        }
    }
}
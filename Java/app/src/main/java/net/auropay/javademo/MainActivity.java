package net.auropay.javademo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.auropay.javademo.databinding.ActivityMainBinding;
import net.auropay.payments.AuroPay;
import net.auropay.payments.domain.enums.Country;
import net.auropay.payments.domain.service.CustomerProfile;
import net.auropay.payments.domain.service.PaymentResultListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        initializations();
    }

    private void initializations() {
        binding.buttonPay.setOnClickListener(v -> {

            // Get AuroPay builder.
            AuroPay auroPay = new AuroPay.Initialize(
                    "Your merchant id",
                    "Your access key",
                    "Your secret key",
                    getCustomerProfile())
                    .autoContrast(true)
                    .country(Country.IN)
                    .showReceipt(true)
                    .build();

            // Initiate payment request.
            auroPay.doPayment(
                    this,
                    1500,
                    "",
                    new PaymentResultListener() {
                        @Override
                        public void onSuccess(@NonNull String orderId, int transactionStatus, @NonNull String transactionId) {
                            // Handle payment success.
                            Toast.makeText(context, "Success: " + transactionId, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull String message2) {
                            // Handle payment failure.
                            Toast.makeText(context, "Failed: $message", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private CustomerProfile getCustomerProfile() {
        return new CustomerProfile(
                "Some Title",
                binding.etFirstName.getText().toString(),
                "",
                binding.etLastName.getText().toString(),
                binding.etPhone.getText().toString(),
                binding.etEmail.getText().toString()
        );
    }
}
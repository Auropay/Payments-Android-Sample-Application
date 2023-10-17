package net.auropay.javademo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.auropay.javademo.databinding.ActivityMainBinding;
import net.auropay.payments.AuroPay;
import net.auropay.payments.domain.enums.Country;
import net.auropay.payments.domain.service.CustomerProfile;
import net.auropay.payments.domain.service.EventListener;
import net.auropay.payments.domain.service.PaymentResultListener;
import net.auropay.payments.domain.service.Theme;

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

            // Additional event listener.
            EventListener eventListener = (eventId, eventDesc) -> Log.d("AuroPay Event", eventId + " - " + eventDesc);

            // Customize the AuroPay UI colors to match the hosting application theme.
            Theme theme = new Theme("#236C6C", "#FFFFFF", "#FFFFFF", "#FAAB03");

            // Initialize and get the AuroPay instance using builder.
            AuroPay auroPay = new AuroPay.Initialize()
                    .subDomainId("Your subDomainId")
                    .accessKey("Your accessKey")
                    .secretKey("Your secretKey")
                    .customerProfile(getCustomerProfile())
                    .country(Country.IN)
                    .showReceipt(true)
                    .addEventListener(eventListener)
                    .theme(theme)
                    .build();

            // Initiate payment request.
            auroPay.doPayment(this,
                    Double.parseDouble(binding.etAmount.getText().toString()),
                    "xyz123",
                    true,
                    // Consume the payment request result.
                    new PaymentResultListener() {
                        @Override
                        public void onSuccess(@NonNull String orderId, int transactionStatus, @NonNull String transactionId) {
                            // Handle payment success result.
                            Toast.makeText(context, "Success: " + transactionId, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull String message) {
                            // Handle payment failure result.
                            Toast.makeText(context, "Failed: " + message, Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    // Create customer profile model.
    private CustomerProfile getCustomerProfile() {
        return new CustomerProfile(
                "Title",
                binding.etFirstName.getText().toString(),
                "",
                binding.etLastName.getText().toString(),
                binding.etPhone.getText().toString(),
                binding.etEmail.getText().toString()
        );
    }
}
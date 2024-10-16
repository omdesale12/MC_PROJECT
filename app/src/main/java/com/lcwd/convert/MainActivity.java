package com.lcwd.convert;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lcwd.convert.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    double fromValue = 0.0;
    String fromUnit = "";

    double toValue = 0.0;

    String toUnit = "";


    private List<String> units = Arrays.asList(
            "INR", // Indian Rupee
            "USD", // US Dollar
            "EUR", // Euro
            "GBP", // British Pound
            "JPY", // Japanese Yen
            "AUD", // Australian Dollar
            "CAD", // Canadian Dollar
            "CNY", // Chinese Yuan
            "CHF", // Swiss Franc
            "NZD", // New Zealand Dollar
            "ZAR"  // South African Rand
    );


    private Map<String, Map<String, Double>> exchangeRates = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeExchangeRates();
        initComponents();

    }

    private void initializeExchangeRates() {
        exchangeRates.put("INR", new HashMap<>());
        exchangeRates.get("INR").put("USD", 1 / 83.22);
        exchangeRates.get("INR").put("EUR", 0.011); // 1 INR = 0.011 EUR (example)
        exchangeRates.get("INR").put("GBP", 0.0098); // 1 INR = 0.0098 GBP (example)
        exchangeRates.get("INR").put("JPY", 0.78); // 1 INR = 0.78 JPY (example)
        exchangeRates.get("INR").put("AUD", 0.017); // 1 INR = 0.017 AUD (example)
        exchangeRates.get("INR").put("CAD", 0.014); // 1 INR = 0.014 CAD (example)
        exchangeRates.get("INR").put("CNY", 0.074); // 1 INR = 0.074 CNY (example)
        exchangeRates.get("INR").put("CHF", 0.010); // 1 INR = 0.010 CHF (example)
        exchangeRates.get("INR").put("NZD", 0.016); // 1 INR = 0.016 NZD (example)
        exchangeRates.get("INR").put("ZAR", 0.19); // 1 INR = 0.19 ZAR (example)

        // USD conversions
        exchangeRates.put("USD", new HashMap<>());
        exchangeRates.get("USD").put("INR", 83.22);
        exchangeRates.get("USD").put("EUR", 1 / 1.06);
        exchangeRates.get("USD").put("GBP", 1 / 1.22);
        exchangeRates.get("USD").put("JPY", 150.51);
        exchangeRates.get("USD").put("AUD", 1.54); // 1 USD = 1.54 AUD (example)
        exchangeRates.get("USD").put("CAD", 1.36); // 1 USD = 1.36 CAD (example)
        exchangeRates.get("USD").put("CNY", 7.14); // 1 USD = 7.14 CNY (example)
        exchangeRates.get("USD").put("CHF", 0.92); // 1 USD = 0.92 CHF (example)
        exchangeRates.get("USD").put("NZD", 1.66); // 1 USD = 1.66 NZD (example)
        exchangeRates.get("USD").put("ZAR", 18.75); // 1 USD = 18.75 ZAR (example)

        // EUR conversions
        exchangeRates.put("EUR", new HashMap<>());
        exchangeRates.get("EUR").put("INR", 90.32); // 1 EUR = 90.32 INR (example)
        exchangeRates.get("EUR").put("USD", 1.06);
        exchangeRates.get("EUR").put("GBP", 0.85); // 1 EUR = 0.85 GBP (example)
        exchangeRates.get("EUR").put("JPY", 159.64); // 1 EUR = 159.64 JPY (example)
        exchangeRates.get("EUR").put("AUD", 1.45); // 1 EUR = 1.45 AUD (example)
        exchangeRates.get("EUR").put("CAD", 1.28); // 1 EUR = 1.28 CAD (example)
        exchangeRates.get("EUR").put("CNY", 6.73); // 1 EUR = 6.73 CNY (example)
        exchangeRates.get("EUR").put("CHF", 1.02); // 1 EUR = 1.02 CHF (example)
        exchangeRates.get("EUR").put("NZD", 1.60); // 1 EUR = 1.60 NZD (example)
        exchangeRates.get("EUR").put("ZAR", 22.00); // 1 EUR = 22.00 ZAR (example)

        // GBP conversions
        exchangeRates.put("GBP", new HashMap<>());
        exchangeRates.get("GBP").put("INR", 102.16); // 1 GBP = 102.16 INR (example)
        exchangeRates.get("GBP").put("USD", 1.22);
        exchangeRates.get("GBP").put("EUR", 1.18); // 1 GBP = 1.18 EUR (example)
        exchangeRates.get("GBP").put("JPY", 187.17); // 1 GBP = 187.17 JPY (example)
        exchangeRates.get("GBP").put("AUD", 1.71); // 1 GBP = 1.71 AUD (example)
        exchangeRates.get("GBP").put("CAD", 1.47); // 1 GBP = 1.47 CAD (example)
        exchangeRates.get("GBP").put("CNY", 7.92); // 1 GBP = 7.92 CNY (example)
        exchangeRates.get("GBP").put("CHF", 1.20); // 1 GBP = 1.20 CHF (example)
        exchangeRates.get("GBP").put("NZD", 1.89); // 1 GBP = 1.89 NZD (example)
        exchangeRates.get("GBP").put("ZAR", 25.72); // 1 GBP = 25.72 ZAR (example)

        // JPY conversions
        exchangeRates.put("JPY", new HashMap<>());
        exchangeRates.get("JPY").put("INR", 1.28); // 1 JPY = 1.28 INR (example)
        exchangeRates.get("JPY").put("USD", 0.0067);
        exchangeRates.get("JPY").put("EUR", 0.0063); // 1 JPY = 0.0063 EUR (example)
        exchangeRates.get("JPY").put("GBP", 0.0053); // 1 JPY = 0.0053 GBP (example)
        exchangeRates.get("JPY").put("AUD", 0.014); // 1 JPY = 0.014 AUD (example)
        exchangeRates.get("JPY").put("CAD", 0.012); // 1 JPY = 0.012 CAD (example)
        exchangeRates.get("JPY").put("CNY", 0.048); // 1 JPY = 0.048 CNY (example)
        exchangeRates.get("JPY").put("CHF", 0.0066); // 1 JPY = 0.0066 CHF (example)
        exchangeRates.get("JPY").put("NZD", 0.013); // 1 JPY = 0.013 NZD (example)
        exchangeRates.get("JPY").put("ZAR", 0.17); // 1 JPY = 0.17 ZAR (example)

        // AUD conversions
        exchangeRates.put("AUD", new HashMap<>());
        exchangeRates.get("AUD").put("INR", 59.09); // 1 AUD = 59.09 INR (example)
        exchangeRates.get("AUD").put("USD", 0.64);
        exchangeRates.get("AUD").put("EUR", 0.69); // 1 AUD = 0.69 EUR (example)
        exchangeRates.get("AUD").put("GBP", 0.58); // 1 AUD = 0.58 GBP (example)
        exchangeRates.get("AUD").put("JPY", 72.15); // 1 AUD = 72.15 JPY (example)
        exchangeRates.get("AUD").put("CAD", 0.87); // 1 AUD = 0.87 CAD (example)
        exchangeRates.get("AUD").put("CNY", 4.37); // 1 AUD = 4.37 CNY (example)
        exchangeRates.get("AUD").put("CHF", 0.68); // 1 AUD = 0.68 CHF (example)
        exchangeRates.get("AUD").put("NZD", 1.08); // 1 AUD = 1.08 NZD (example)
        exchangeRates.get("AUD").put("ZAR", 16.63); // 1 AUD = 16.63 ZAR (example)

        // CAD conversions
        exchangeRates.put("CAD", new HashMap<>());
        exchangeRates.get("CAD").put("INR", 73.49); // 1 CAD = 73.49 INR (example)
        exchangeRates.get("CAD").put("USD", 0.73);
        exchangeRates.get("CAD").put("EUR", 0.78); // 1 CAD = 0.78 EUR (example)
        exchangeRates.get("CAD").put("GBP", 0.68); // 1 CAD = 0.68 GBP (example)
        exchangeRates.get("CAD").put("JPY", 83.79); // 1 CAD = 83.79 JPY (example)
        exchangeRates.get("CAD").put("AUD", 1.15); // 1 CAD = 1.15 AUD (example)
        exchangeRates.get("CAD").put("CNY", 4.99); // 1 CAD = 4.99 CNY (example)
        exchangeRates.get("CAD").put("CHF", 0.78); // 1 CAD = 0.78 CHF (example)
        exchangeRates.get("CAD").put("NZD", 1.24); // 1 CAD = 1.24 NZD (example)
        exchangeRates.get("CAD").put("ZAR", 18.82); // 1 CAD = 18.82 ZAR (example)

        // CNY conversions
        exchangeRates.put("CNY", new HashMap<>());
        exchangeRates.get("CNY").put("INR", 13.53); // 1 CNY = 13.53 INR (example)
        exchangeRates.get("CNY").put("USD", 0.14);
        exchangeRates.get("CNY").put("EUR", 0.15); // 1 CNY = 0.15 EUR (example)
        exchangeRates.get("CNY").put("GBP", 0.13); // 1 CNY = 0.13 GBP (example)
        exchangeRates.get("CNY").put("JPY", 20.71); // 1 CNY = 20.71 JPY (example)
        exchangeRates.get("CNY").put("AUD", 0.23); // 1 CNY = 0.23 AUD (example)
        exchangeRates.get("CNY").put("CAD", 0.20); // 1 CNY = 0.20 CAD (example)
        exchangeRates.get("CNY").put("CHF", 0.15); // 1 CNY = 0.15 CHF (example)
        exchangeRates.get("CNY").put("NZD", 0.23); // 1 CNY = 0.23 NZD (example)
        exchangeRates.get("CNY").put("ZAR", 3.76); // 1 CNY = 3.76 ZAR (example)

        // CHF conversions
        exchangeRates.put("CHF", new HashMap<>());
        exchangeRates.get("CHF").put("INR", 101.10); // 1 CHF = 101.10 INR (example)
        exchangeRates.get("CHF").put("USD", 1.09);
        exchangeRates.get("CHF").put("EUR", 0.98); // 1 CHF = 0.98 EUR (example)
        exchangeRates.get("CHF").put("GBP", 0.83); // 1 CHF = 0.83 GBP (example)
        exchangeRates.get("CHF").put("JPY", 151.60); // 1 CHF = 151.60 JPY (example)
        exchangeRates.get("CHF").put("AUD", 1.47); // 1 CHF = 1.47 AUD (example)
        exchangeRates.get("CHF").put("CAD", 1.28); // 1 CHF = 1.28 CAD (example)
        exchangeRates.get("CHF").put("CNY", 6.73); // 1 CHF = 6.73 CNY (example)
        exchangeRates.get("CHF").put("NZD", 1.58); // 1 CHF = 1.58 NZD (example)
        exchangeRates.get("CHF").put("ZAR", 21.32); // 1 CHF = 21.32 ZAR (example)

        // NZD conversions
        exchangeRates.put("NZD", new HashMap<>());
        exchangeRates.get("NZD").put("INR", 62.50); // 1 NZD = 62.50 INR (example)
        exchangeRates.get("NZD").put("USD", 0.60);
        exchangeRates.get("NZD").put("EUR", 0.63); // 1 NZD = 0.63 EUR (example)
        exchangeRates.get("NZD").put("GBP", 0.53); // 1 NZD = 0.53 GBP (example)
        exchangeRates.get("NZD").put("JPY", 79.77); // 1 NZD = 79.77 JPY (example)
        exchangeRates.get("NZD").put("AUD", 0.93); // 1 NZD = 0.93 AUD (example)
        exchangeRates.get("NZD").put("CAD", 0.80); // 1 NZD = 0.80 CAD (example)
        exchangeRates.get("NZD").put("CNY", 4.25); // 1 NZD = 4.25 CNY (example)
        exchangeRates.get("NZD").put("CHF", 0.63); // 1 NZD = 0.63 CHF (example)
        exchangeRates.get("NZD").put("ZAR", 17.53); // 1 NZD = 17.53 ZAR (example)

        // ZAR conversions
        exchangeRates.put("ZAR", new HashMap<>());
        exchangeRates.get("ZAR").put("INR", 5.27); // 1 ZAR = 5.27 INR (example)
        exchangeRates.get("ZAR").put("USD", 0.053);
        exchangeRates.get("ZAR").put("EUR", 0.045); // 1 ZAR = 0.045 EUR (example)
        exchangeRates.get("ZAR").put("GBP", 0.039); // 1 ZAR = 0.039 GBP (example)
        exchangeRates.get("ZAR").put("JPY", 5.89); // 1 ZAR = 5.89 JPY (example)
        exchangeRates.get("ZAR").put("AUD", 0.06); // 1 ZAR = 0.06 AUD (example)
        exchangeRates.get("ZAR").put("CAD", 0.053); // 1 ZAR = 0.053 CAD (example)
        exchangeRates.get("ZAR").put("CNY", 0.26); // 1 ZAR = 0.26 CNY (example)
        exchangeRates.get("ZAR").put("CHF", 0.047); // 1 ZAR = 0.047 CHF (example)
        exchangeRates.get("ZAR").put("NZD", 0.057);
    }

    private void initComponents() {
        Collections.sort(units);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units);
        binding.toUnit.setAdapter(arrayAdapter);
        binding.fromUnit.setAdapter(arrayAdapter);

        binding.convertButton.setOnClickListener(view -> {
//            get the values
            try {
                fromValue = Double.parseDouble(binding.fromValue.getText().toString());
                fromUnit = binding.fromUnit.getSelectedItem().toString();
                toUnit = binding.toUnit.getSelectedItem().toString();

                double convertedValue = convertCurrency(fromUnit,toUnit,fromValue);
                binding.toValue.setText(String.valueOf(convertedValue));
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void convertValue() {

        try {
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue / 83.22; // INR to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 83.22; // USD to INR

            } else if (fromUnit.equalsIgnoreCase("EUR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 1.06; // EUR to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("EUR")) {
                toValue = fromValue / 1.06; // USD to EUR

            } else if (fromUnit.equalsIgnoreCase("GBP") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 1.22; // GBP to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("GBP")) {
                toValue = fromValue / 1.22; // USD to GBP

            } else if (fromUnit.equalsIgnoreCase("JPY") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.0067; // JPY to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("JPY")) {
                toValue = fromValue / 0.0067; // USD to JPY

            } else if (fromUnit.equalsIgnoreCase("AUD") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.64; // AUD to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("AUD")) {
                toValue = fromValue / 0.64; // USD to AUD

            } else if (fromUnit.equalsIgnoreCase("CAD") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.73; // CAD to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("CAD")) {
                toValue = fromValue / 0.73; // USD to CAD

            } else if (fromUnit.equalsIgnoreCase("CNY") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.14; // CNY to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("CNY")) {
                toValue = fromValue / 0.14; // USD to CNY

            } else if (fromUnit.equalsIgnoreCase("CHF") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 1.1; // CHF to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("CHF")) {
                toValue = fromValue / 1.1; // USD to CHF

            } else if (fromUnit.equalsIgnoreCase("NZD") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.59; // NZD to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("NZD")) {
                toValue = fromValue / 0.59; // USD to NZD

            } else if (fromUnit.equalsIgnoreCase("ZAR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 0.053; // ZAR to USD
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("ZAR")) {
                toValue = fromValue / 0.053; // USD to ZAR

            } else {
                // If the conversion is not handled, you can set a default error or handling
                Toast.makeText(this, "Currency conversion not available", Toast.LENGTH_LONG).show();
            }

            // Display the converted value
            binding.toValue.setText(String.valueOf(toValue));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private double convertCurrency(String fromUnit, String toUnit, double fromValue) throws Exception {
        // Check if the exchange rates for the source currency exist
        if (!exchangeRates.containsKey(fromUnit)) {
            throw new Exception("Conversion from " + fromUnit + " not available");
        }

        // Check if the target currency conversion rate exists
        if (!exchangeRates.get(fromUnit).containsKey(toUnit)) {
            throw new Exception("Conversion to " + toUnit + " not available");
        }

        // Perform the conversion
        double conversionRate = exchangeRates.get(fromUnit).get(toUnit);
        return fromValue * conversionRate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
}
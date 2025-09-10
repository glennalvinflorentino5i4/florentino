package com.example.florentino;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // UI fields
    private EditText number1EditText, number2EditText;
    private TextView resultTextView;
    private Button addButton, subtractButton, multiplyButton, divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply padding for system bars (status/navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupUIComponents();
        setupButtonListeners();
    }

    private void setupUIComponents() {
        number1EditText = findViewById(R.id.number1_edit_text);
        number2EditText = findViewById(R.id.number2_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        addButton = findViewById(R.id.addButton1);
        subtractButton = findViewById(R.id.subtractButton1);
        multiplyButton = findViewById(R.id.multiplyButton1);
        divideButton = findViewById(R.id.divideButton1);
    }

    private void setupButtonListeners() {
        addButton.setOnClickListener(v -> calculate('+'));
        subtractButton.setOnClickListener(v -> calculate('-'));
        multiplyButton.setOnClickListener(v -> calculate('*'));
        divideButton.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operation) {
        // Avoid crashing if input is empty
        if (number1EditText.getText().toString().isEmpty() ||
                number2EditText.getText().toString().isEmpty()) {
            resultTextView.setText("Please enter numbers");
            return;
        }

        double num1 = parseDouble(number1EditText.getText().toString());
        double num2 = parseDouble(number2EditText.getText().toString());
        double result = 0;

        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultTextView.setText("Error: Division by zero");
                    return;
                }
                break;
        }

        resultTextView.setText("Result: " + result);
    }
}

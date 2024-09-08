package com.example.xyuculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                if (buttonText.equals("C")) {
                    resetCalculator();
                } else if (buttonText.equals("=")) {
                    calculateResult();
                } else if (buttonText.equals("+") || buttonText.equals("-") ||
                        buttonText.equals("*") || buttonText.equals("/")) {
                    if (!isOperatorPressed) {
                        firstNumber = Double.parseDouble(currentInput);
                        operator = buttonText;
                        isOperatorPressed = true;
                        currentInput = "";
                    }
                } else {
                    currentInput += buttonText;
                    display.setText(currentInput);
                }
            }
        };

        // Добавляем обработчики на кнопки
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnEqual, R.id.btnClear, R.id.btnDot
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void resetCalculator() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        isOperatorPressed = false;
        display.setText("");
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
        isOperatorPressed = false;
    }
}

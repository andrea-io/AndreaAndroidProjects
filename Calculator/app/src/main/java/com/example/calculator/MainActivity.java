package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public double applyOperation(char operation, double a, double b) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'X':
                return a * b;
            case '/':
                if(b == 0) {
                    throw new UnsupportedOperationException("Cannot divide a number by zero")
                }
                return a / b;
        }

        return 0;
    }

    public boolean hasPrecedence(char operationOne, char operationTwo) {
        if((operationOne == 'X' || operationOne == '/') && (operationTwo == '+' || operationTwo == '-')) {
            return false;
        }

        return true;
    }
}
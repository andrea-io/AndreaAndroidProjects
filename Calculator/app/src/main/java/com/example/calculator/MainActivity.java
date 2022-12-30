package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Number buttons
        Button one = findViewById(R.id.oneButton);
        one.setOnClickListener(this);

        Button two = findViewById(R.id.twoButton);
        two.setOnClickListener(this);

        Button three = findViewById(R.id.threeButton);
        three.setOnClickListener(this);

        Button four = findViewById(R.id.fourButton);
        four.setOnClickListener(this);

        Button five = findViewById(R.id.fiveButton);
        five.setOnClickListener(this);

        Button six = findViewById(R.id.sixButton);
        six.setOnClickListener(this);

        Button seven = findViewById(R.id.sevenButton);
        seven.setOnClickListener(this);

        Button eight = findViewById(R.id.eightButton);
        eight.setOnClickListener(this);

        Button nine = findViewById(R.id.nineButton);
        nine.setOnClickListener(this);

        Button zero = findViewById(R.id.zeroButton);
        zero.setOnClickListener(this);

        Button add = findViewById(R.id.addButton);
        add.setOnClickListener(this);

        Button multiply = findViewById(R.id.multiplyButton);
        multiply.setOnClickListener(this);

        Button subtract = findViewById(R.id.subtractButton);
        subtract.setOnClickListener(this);

        Button divide = findViewById(R.id.divideButton);
        divide.setOnClickListener(this);

        Button clear = findViewById(R.id.clearButton);
        clear.setOnClickListener(this);

        Button equal = findViewById(R.id.equalButton);
        equal.setOnClickListener(this);
    }

    int operandCount = 0;

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.oneButton:
                input += "1";
                updateTotal();
                break;
            case R.id.twoButton:
                input += "2";
                updateTotal();
                break;
            case R.id.threeButton:
                input += "3";
                updateTotal();
                break;
            case R.id.fourButton:
                input += "4";
                updateTotal();
                break;
            case R.id.fiveButton:
                input += "5";
                updateTotal();
                break;
            case R.id.sixButton:
                input += "6";
                updateTotal();
                break;
            case R.id.sevenButton:
                input += "7";
                updateTotal();
                break;
            case R.id.eightButton:
                input += "8";
                updateTotal();
                break;
            case R.id.nineButton:
                input += "9";
                updateTotal();
                break;
            case R.id.zeroButton:
                input += "0";
                updateTotal();
                break;
            case R.id.addButton:
                input += " + ";
                operandCount += 1;

                if(operandCount < 2) {
                    break;
                }

                updateResult();
                operandCount = 0;
                break;
            case R.id.subtractButton:
                input += " - ";
                operandCount += 1;

                if(operandCount < 2) {
                    break;
                }

                updateResult();
                operandCount--;
                break;
            case R.id.divideButton:
                input += " / ";
                operandCount += 1;

                if(operandCount < 2) {
                    break;
                }

                updateResult();
                operandCount--;
                break;
            case R.id.multiplyButton:
                input += " X ";
                operandCount += 1;

                if(operandCount < 2) {
                    break;
                }

                updateResult();
                operandCount--;
                break;
            case R.id.clearButton:
                TextView totalText = findViewById(R.id.calculatorTextView);
                input = "";
                totalText.setText("");
                operandCount = 0;
            case R.id.equalButton:
                updateResult();
                operandCount = 0;
                break;
            default:
                break;
        }
    }

    void updateTotal() {
        TextView totalText = findViewById(R.id.calculatorTextView);
        totalText.setText(input);
    }

    void updateResult() {
        TextView totalText = findViewById(R.id.calculatorTextView);

        System.out.println("Before Input: " + input);
        double result = parseExpression(input);
        System.out.println("Result: " + result);
        totalText.setText(String.valueOf(result));

        input = Double.toString(result);
        System.out.println("New input: " + input);
    }

    public double parseExpression(String expression) {
        Stack<Double> operands = new Stack<Double>();
        Stack<Character> operators = new Stack<Character>();

        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            }

            if(c == '+' || c == '-' || c == 'X' || c == '/') {
                // c is an operator

                while(!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    operands.push(applyOperation(operators.pop(), operands));
                }

                operators.push(c);
            } else if(Character.isDigit(c)) {
                // c is a digit

                StringBuilder operand = new StringBuilder();

                while(i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    operand.append(expression.charAt(i));
                    i++;
                }

                i--;
                operands.push(Double.parseDouble(operand.toString()));
            }
        }

        while(!operators.isEmpty()) {
            operands.push(applyOperation(operators.pop(), operands));
        }

        return operands.pop();
    }
    public double applyOperation(char operation, Stack<Double> operands) {
        if(operands.size() < 2) {
            throw new IllegalArgumentException("Not enough operands to perform operation");
        }

        double b = operands.pop();
        double a = operands.pop();

        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'X':
                return a * b;
            case '/':
                if(b == 0) {
                    throw new UnsupportedOperationException("Cannot divide a number by zero");
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
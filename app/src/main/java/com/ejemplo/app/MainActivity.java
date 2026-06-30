package com.ejemplo.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView display;
    StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnDot, R.id.btnEqual, R.id.btnC, R.id.btnBack,
                R.id.btnLeftParen, R.id.btnRightParen,
                R.id.btnSin, R.id.btnCos, R.id.btnTan, R.id.btnLog,
                R.id.btnPi, R.id.btnE, R.id.btnSquare, R.id.btnSqrt
        };
        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String text = b.getText().toString();
        switch (text) {
            case "C":
                expression.setLength(0);
                display.setText("0");
                break;
            case "⌫":
                if (expression.length() > 0) {
                    expression.deleteCharAt(expression.length() - 1);
                    display.setText(expression.toString());
                }
                break;
            case "=":
                try {
                    double result = new ExpressionBuilder(expression.toString()).build().evaluate();
                    if (result == (long) result) {
                        display.setText(String.valueOf((long) result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    expression.setLength(0);
                    expression.append(result);
                } catch (Exception e) {
                    Toast.makeText(this, "Error en la expresión", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                switch (text) {
                    case "sin": expression.append("sin("); break;
                    case "cos": expression.append("cos("); break;
                    case "tan": expression.append("tan("); break;
                    case "log": expression.append("log10("); break;
                    case "√": expression.append("sqrt("); break;
                    case "x²": expression.append("^2"); break;
                    case "π": expression.append("π"); break;
                    case "e": expression.append("e"); break;
                    case "÷": expression.append("/"); break;
                    case "×": expression.append("*"); break;
                    default: expression.append(text); break;
                }
                display.setText(expression.toString());
                break;
        }
    }
}

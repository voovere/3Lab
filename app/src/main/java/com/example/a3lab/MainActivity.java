package com.example.a3lab;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    String num = "0";
    String memNum = "0";

    public TextView tvMain;
    public TextView tvMem;
    public TextView tvChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare textViews
        tvMain = findViewById(R.id.tvMain);
        tvMem = findViewById(R.id.tvMem);
        tvChar = findViewById(R.id.tvChar);
        //init main textView
        tvMain.setText(String.valueOf(num));
    }

    //function to calculate values
    @SuppressLint("DefaultLocale")
    public void Calc(double number, double memNumber){
        switch (tvChar.getText().toString()){
            case "+":
                number = memNumber + number;
                break;
            case "-":
                number = memNumber - number;
                break;
            case "*":
                number = memNumber * number;
                break;
            case "/":
                if(number != 0){
                    number = memNumber / number;
                }
                break;
            default:
                break;
        }
        num = String.format("%.2f", number);
    }

    //function for number buttons
    public void bttnNumClick(View view){
        Button bttn = (Button)view;
        String bttnText = bttn.getText().toString();

        if(num.length() <= 8) {
            if (num.equals("0")) {
                num = bttnText;
            } else {
                num += bttnText;
            }
            //set number on screen
            tvMain.setText(num);
        }
    }

    //function for delete buttons
    public void bttnDelClick(View view){
        Button bttn = (Button)view;
        String bttnText = bttn.getText().toString();

        //button to clear everything
        if(bttnText.equals("C")){
            num = "0";
            memNum = "0";
            tvMem.setText("");
            tvChar.setText("");
        }
        //backspace
        else{
            if(num.length() >= 2){
                num = num.substring(0, num.length() - 1);
            }
            else{
                num = memNum;
                memNum = "0";
                tvChar.setText("");
                tvMem.setText("");
            }
        }
        tvMain.setText(num);
    }

    //function for symbols
    @SuppressLint("SetTextI18n")
    public void bttnCharClick(View view){
        Button bttn = (Button)view;
        String bttnText = bttn.getText().toString();

        if(!tvChar.getText().toString().equals("")){
            Calc(Double.parseDouble(num), Double.parseDouble(memNum));
            memNum = num;
            num = "0";
        }
        //root of number
        else if(bttnText.equals("√")){
            if(Double.parseDouble(num) >= 0){
                memNum = num;
                num = String.valueOf(sqrt(Double.parseDouble(num)));
            }
            else{
                tvMain.setText("ERROR");
                memNum = "0";
                num = "0";
                return;
            }
        }
        else{
            memNum = num;
            num = "0";
        }
        //set text
        tvMem.setText(memNum);
        tvChar.setText(bttnText);
        tvMain.setText(num);
    }

    //function for equal button
    public void bttnEqlClick(View view){
        Calc(Double.parseDouble(num), Double.parseDouble(memNum));
        memNum = "0";
        tvMem.setText("");
        tvChar.setText("");
        tvMain.setText(num);
    }

    //change positive to negative
    public void bttnSignClick(View view){
        num = String.valueOf(Double.parseDouble(num)*-1);
        tvMain.setText(num);
    }


}
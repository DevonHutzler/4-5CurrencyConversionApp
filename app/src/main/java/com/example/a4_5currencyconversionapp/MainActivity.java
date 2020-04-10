package com.example.a4_5currencyconversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double converted, convert;
    char symbol;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //icon stuff p.140
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText dollars = (EditText) findViewById(R.id.moneyInput);
        final RadioButton euro = (RadioButton) findViewById(R.id.euroRB);
        final RadioButton mex = (RadioButton) findViewById(R.id.mexRB);
        final RadioButton can = (RadioButton) findViewById(R.id.canRB);
        final TextView display = (TextView) findViewById(R.id.outputDisplay);
        final Button getConversion = (Button) findViewById(R.id.btnConvert);
        getConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(""); //reset
                try {
                    double usd = Double.parseDouble(dollars.getText().toString());
                    if (usd < 100001) { //max 100k USD
                        /*conversion rates
                        euro: 0.91496 €
                        canada: 1.39748
                        mexico: 23.5565     */
                        symbol = '$';
                        if (euro.isChecked() || mex.isChecked() || can.isChecked()) {
                            if (euro.isChecked()) {
                                convert = 0.91496;
                                symbol = '€';
                                code = " Euros";
                            } else if (mex.isChecked()) {
                                convert = 23.5565;
                                code = " MXN";
                            } else if (can.isChecked()) {
                                convert = 1.39748;
                                code = " CAD";
                            }
                            converted = usd * convert;
                            DecimalFormat money = new DecimalFormat("###,###.00");
                            display.setText("$" + money.format(usd) + " USD is worth\n" + symbol + money.format(converted)+ code);
                        } else {
                            Toast.makeText(MainActivity.this, "Select a choice", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Enter amount less than $100k", Toast.LENGTH_LONG).show();
                    }
                } catch (IllegalArgumentException ex) {
                    Toast.makeText(MainActivity.this, "Enter USD amount to convert", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}

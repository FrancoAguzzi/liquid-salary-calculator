package com.example.calculadorasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class mainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void showResults(View view) {
        EditText salaryInput = (EditText)findViewById(R.id.salaryInput);
        EditText dependentsInput = (EditText)findViewById(R.id.dependentsInput);
        EditText discountsInput = (EditText)findViewById(R.id.discountsInput);

        String salaryStr = salaryInput.getText().toString();
        String dependentsStr = dependentsInput.getText().toString();
        String discountsStr = discountsInput.getText().toString();

        Intent resultsIntent = new Intent(this, resultsScreen.class);

        if (salaryStr.isEmpty())
            salaryStr = "0";
        if (dependentsStr.isEmpty())
            dependentsStr = "0";
        if (discountsStr.isEmpty())
            discountsStr = "0";

        resultsIntent.putExtra("SALARY_INPUT", salaryStr);
        resultsIntent.putExtra("DEPENDENTS_INPUT", dependentsStr);
        resultsIntent.putExtra("DISCOUNTS_INPUT", discountsStr);
        startActivity(resultsIntent);
    }
}
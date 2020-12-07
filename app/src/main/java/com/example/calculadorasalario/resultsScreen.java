package com.example.calculadorasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class resultsScreen extends AppCompatActivity {

    private double salary;
    private int dependents;
    private double discounts;
    private double inss;
    private double irrf;
    private double liquid_salary;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        Bundle extras = getIntent().getExtras();
        String salaryStr = extras.getString("SALARY_INPUT");
        salary = Double.parseDouble(salaryStr);
        String dependentsStr = extras.getString("DEPENDENTS_INPUT");
        dependents = Integer.parseInt(dependentsStr);
        String discountsStr = extras.getString("DISCOUNTS_INPUT");
        discounts = Double.parseDouble(discountsStr);

        this.calculateInss();
        double base = salary - inss - (dependents * 189.59);
        this.calculateIrrf(base);
        this.calculateLiquidSalary();

        TextView grossSalaryView = findViewById(R.id.salary_result);
        grossSalaryView.setText(String.valueOf(salary));
        TextView irrfView = findViewById(R.id.irrf_result);
        irrfView.setText(String.format("-%s", df.format(irrf)));
        TextView inssView = findViewById(R.id.inss_result);
        inssView.setText(String.format("-%s", df.format(inss)));
        TextView liquidSalaryView = findViewById(R.id.liquidSalary_result);
        liquidSalaryView.setText(String.valueOf(df.format(liquid_salary)));
        TextView discountsView = findViewById(R.id.discounts_result);
        discountsView.setText(String.format("-%s", df.format(discounts)));
    }


    private double calculateInss() {
        if (salary <= 1045) {
            inss = salary * 0.075;
        } else if (salary <= 2089.6) {
            inss = (salary * 0.09) - 15.67;
        } else if (salary <= 3134.4) {
            inss = (salary * 0.12) - 78.36;
        } else if (salary <= 6101.06) {
            inss = (salary * 0.14) - 141.05;
        } else {
            inss = 713.1;
        }
        return inss;
    }

    private double calculateIrrf(double base) {
        if (base <= 1903.98) {
            irrf = 0;
        } else if (base <= 2826.65) {
            irrf = (base * 0.075) - 142.8;
        } else if (base <= 3751.05) {
            irrf = (base * 0.15) - 354.8;
        } else if (base <= 4664.68) {
            irrf = (base * 0.225) - 636.13;
        } else {
            irrf = (base * 0.275) - 869.36;
        }
        return irrf;
    }

    private double calculateLiquidSalary() {
        liquid_salary = salary - inss - irrf - discounts;
        return liquid_salary;
    }

    public void backToMain(View view) {
        Intent mainIntent = new Intent(this, mainScreen.class);
        startActivity(mainIntent);
    }
}
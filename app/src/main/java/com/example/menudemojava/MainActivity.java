package com.example.menudemojava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Элементы экрана
    TextView tvInfo, mode;
    EditText etInput;
    Button bControl, exit, hardMode, easyMode;
    Random rand = new Random();
    int number = rand.nextInt(100) + 1;
    int maxValue = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим элементы
        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);
        exit = findViewById(R.id.exit);
        hardMode = findViewById(R.id.hardMode);
        easyMode = findViewById(R.id.easyMode);
        mode = findViewById(R.id.mode);
        mode.setText(getString(R.string.easyMode));
    }

    public void onClick(View view) {

        View.OnClickListener clickListenerExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        };

        exit.setOnClickListener(clickListenerExit);


        View.OnClickListener clickListenerHardMode = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = rand.nextInt(10000) + 1;
                maxValue = 10000;
                mode.setText(getString(R.string.mode));
            }
        };
        hardMode.setOnClickListener(clickListenerHardMode);

        View.OnClickListener clickListenerEasyMode = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = rand.nextInt(100) + 1;
                maxValue = 100;
                mode.setText(getString(R.string.easyMode));
            }
        };
        easyMode.setOnClickListener(clickListenerEasyMode);

        if (etInput.getText().toString().length()==0){
            tvInfo.setText(getString(R.string.suggestion));
        }
        else {
            int value = Integer.parseInt(etInput.getText().toString());
            if ((value < 1) || (value > maxValue)) {
                tvInfo.setText(getString(R.string.errorValue));
            }
            else if (value < number) {
                tvInfo.setText("Больше");
            }
            else if (value > number) {
                tvInfo.setText("Меньше");
            }
            else {
                tvInfo.setText("Ура! Угадал!");
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().toString().equals("Выход")){
            finish();
            System.exit(0);
        }

        if (item.getTitle().toString().equals("Легкий режим")){
            mode.setText(getString(R.string.easyMode));
            tvInfo.setText(getString(R.string.suggestion));
            etInput.setText("");
            number = rand.nextInt(100) + 1;
            maxValue = 100;
        }

        if (item.getTitle().toString().equals("Сложный режим")){
            mode.setText(getString(R.string.mode));
            tvInfo.setText(getString(R.string.suggestion));
            etInput.setText("");
            number = rand.nextInt(10000) + 1;
            maxValue = 10000;
        }

        if (item.getTitle().toString().equals("Новая игра")){
            tvInfo.setText(getString(R.string.suggestion));
            etInput.setText("");
            if (mode.getText().toString().equals(getString(R.string.easyMode))){
                number = rand.nextInt(100) + 1;
                maxValue = 100;
            }
            else {
                number = rand.nextInt(10000) + 1;
                maxValue = 10000;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

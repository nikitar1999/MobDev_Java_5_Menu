package com.example.number;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int pickedNumber;
    boolean gameOver;
    String difficulty = "easy";
    int upperBound = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);

        setupGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem easyCheckable = menu.findItem(R.id.difficulty_easy);
        MenuItem hardCheckable = menu.findItem(R.id.difficulty_hard);
        if (!easyCheckable.isChecked() && !hardCheckable.isChecked())
            easyCheckable.setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_newgame:
                setupGame();
                return true;
            case R.id.action_difficulty:
                return true;
            case R.id.action_exit:
                finish();
                System.exit(0);
                return true;
            case R.id.difficulty_easy:
                setDifficulty("easy");
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            case R.id.difficulty_hard:
                setDifficulty("hard");
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDifficulty(String setting) {
        difficulty = setting;

        switch (setting) {
            case "easy":
                upperBound = 100;
                setupGame();
                return;
            case "hard":
                upperBound = 9000;
                setupGame();
                return;
        }
    }

    private void setupGame() {
        gameOver = false;
        pickedNumber = ThreadLocalRandom.current().nextInt(1, upperBound + 1);
        String tryToGuess = getResources().getString(R.string.try_to_guess, upperBound);
        tvInfo.setText(tryToGuess);
    }

    public void playMore(View view) {
        setupGame();
    }

    public void onClick(View view) {
        if (gameOver) {
            tvInfo.setText("Игра окончена. Сыграть еще?");
            return;
        }

        String inputValue = etInput.getText().toString();
        if (inputValue.isEmpty()) {
            tvInfo.setText(getResources().getString(R.string.error));
            return;
        }

        int value = Integer.parseInt(inputValue);

        if (value < 1 || value > upperBound) {
            tvInfo.setText(getResources().getString(R.string.out_of_range));
            return;
        }

        if (value > pickedNumber) {
            tvInfo.setText(getResources().getString(R.string.ahead));
        } else if (value < pickedNumber) {
            tvInfo.setText(getResources().getString(R.string.behind));
        } else {
            tvInfo.setText(getResources().getString(R.string.hit));
            gameOver = true;
        }

    }
}


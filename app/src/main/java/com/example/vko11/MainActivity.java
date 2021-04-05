package com.example.vko11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    boolean changeText;
    TextView dispText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        dispText = findViewById(R.id.dispText);



        loadSetting();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                loadSetting();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadSetting();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    public void loadSetting(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        changeText = sp.getBoolean("CHANGETEXT", false);
        if (changeText){
            textView.setText(editText.getText());
        }
        float fontSize =Float.parseFloat(sp.getString("FONT", "14"));
        textView.setTextSize(fontSize);

        int textViewWidth =Integer.parseInt(sp.getString("WIDTH", "246"));
        textView.setWidth(textViewWidth);
        int textViewHeight =Integer.parseInt(sp.getString("HEIGHT", "70"));
        textView.setHeight(textViewHeight);

        int maxLines =Integer.parseInt(sp.getString("ROWS_MAX", "1"));
        textView.setMaxLines(maxLines);
        int minLines =Integer.parseInt(sp.getString("ROWS_MIN", "1"));
        textView.setMinLines(minLines);


        dispText.setText(sp.getString("TEXT", ""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.super_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings:
                startSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    public void startSettings(){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
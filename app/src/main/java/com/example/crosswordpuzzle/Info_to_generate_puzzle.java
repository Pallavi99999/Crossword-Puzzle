package com.example.crosswordpuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Info_to_generate_puzzle extends AppCompatActivity {

    Spinner GridSize;
    EditText word1, word2, word3, word4;
    AppCompatButton generatePuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_to_generate_puzzle);

        GridSize = findViewById(R.id.GridSize);
        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);
        word4 = findViewById(R.id.word4);

        generatePuzzle = findViewById(R.id.generatePuzzle);

        generatePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String grid = GridSize.getItemAtPosition(GridSize.getSelectedItemPosition()).toString();
                int i = Integer.parseInt(String.valueOf(grid.charAt(0)));

                if(TextUtils.isEmpty(word1.getText().toString())){
                    word1.setError("word is Required");
                    return;
                }
                if(TextUtils.isEmpty(word2.getText().toString())){
                    word2.setError("word is Required");
                    return;
                }
                if(TextUtils.isEmpty(word3.getText().toString())){
                    word3.setError("word is Required");
                    return;
                }
                if(TextUtils.isEmpty(word4.getText().toString())){
                    word4.setError("word is Required");
                    return;
                }
                if(word1.length() > i){
                    word1.setError("word length can't be more then "+ i);
                    return;
                }
                if(word2.length() > i){
                    word2.setError("word length can't be more then "+   i);
                    return;
                }
                if(word3.length() > i){
                    word3.setError("word length can't be more then "+ i);
                    return;
                }
                if(word4.length() > i){
                    word4.setError("word length can't be more then "+ i);
                    return;
                }

                Intent intent = new Intent(Info_to_generate_puzzle.this, generatedPuzzle.class);
                intent.putExtra("row", String.valueOf(grid.charAt(0)));
                intent.putExtra("col", String.valueOf(grid.charAt(grid.length() - 1)));
                intent.putExtra("word1", word1.getText().toString().toUpperCase());
                intent.putExtra("word2", word2.getText().toString().toUpperCase());
                intent.putExtra("word3", word3.getText().toString().toUpperCase());
                intent.putExtra("word4", word4.getText().toString().toUpperCase());
                startActivity(intent);
            }
        });

    }
}
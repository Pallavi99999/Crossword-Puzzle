package com.example.crosswordpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.Objects;

public class puzzlelevels extends AppCompatActivity {

    AppCompatButton level1, level2, level3, level4, level5;
    TextView UserScore;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzlelevels);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);
        UserScore = findViewById(R.id.UserScore);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fStore.collection("userDetails").document(fAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String score = documentSnapshot.getString("Score");
                        UserScore.setText(documentSnapshot.getString("Score"));
                        int color = Color.parseColor("#D478F2");
                        if (Objects.equals(score, "20")){
                            level1.setEnabled(false);
                            level1.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                        } else if (Objects.equals(score, "40")){
                            level1.setEnabled(false);
                            level1.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level2.setEnabled(false);
                            level2.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                        } else if (Objects.equals(score, "60")){
                            level1.setEnabled(false);
                            level1.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level2.setEnabled(false);
                            level2.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level3.setEnabled(false);
                            level3.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                        } else if (Objects.equals(score, "80")){
                            level1.setEnabled(false);
                            level1.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level2.setEnabled(false);
                            level2.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level3.setEnabled(false);
                            level3.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level4.setEnabled(false);
                            level4.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                        } else if (Objects.equals(score, "100")){
                            level1.setEnabled(false);
                            level1.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level2.setEnabled(false);
                            level2.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level3.setEnabled(false);
                            level3.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level4.setEnabled(false);
                            level4.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                            level5.setEnabled(false);
                            level5.getBackground().setColorFilter(color,  PorterDuff.Mode.MULTIPLY);
                        }
                    }
                });

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puzzlelevels.this, Puzzle1.class);
                startActivity(intent);
                finish();
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puzzlelevels.this, Puzzle2.class);
                startActivity(intent);
                finish();
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puzzlelevels.this, Puzzle3.class);
                startActivity(intent);
                finish();
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puzzlelevels.this, Puzzle4.class);
                startActivity(intent);
                finish();
            }
        });

        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puzzlelevels.this, Puzzle5.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
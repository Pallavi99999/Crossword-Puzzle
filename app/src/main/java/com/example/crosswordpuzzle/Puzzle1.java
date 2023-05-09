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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Puzzle1 extends AppCompatActivity {

    AppCompatButton ans,submit;
    TextView G11, G12, G13, G14, G21, G22, G23, G24, G31, G32,G33, G34, G41, G42, G43, G44;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle1);

        ans = findViewById(R.id.ans);
        submit = findViewById(R.id.submit);
        G11 = findViewById(R.id.G11);
        G12 = findViewById(R.id.G12);
        G13 = findViewById(R.id.G13);
        G14 = findViewById(R.id.G14);
        G21 = findViewById(R.id.G21);
        G22 = findViewById(R.id.G22);
        G23 = findViewById(R.id.G23);
        G24 = findViewById(R.id.G24);
        G31 = findViewById(R.id.G31);
        G32 = findViewById(R.id.G32);
        G33 = findViewById(R.id.G33);
        G34 = findViewById(R.id.G34);
        G41 = findViewById(R.id.G41);
        G42 = findViewById(R.id.G42);
        G43 = findViewById(R.id.G43);
        G44 = findViewById(R.id.G44);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(G12.getText().toString()) || TextUtils.isEmpty(G13.getText().toString()) || TextUtils.isEmpty(G21.getText().toString()) ||
                        TextUtils.isEmpty(G31.getText().toString()) || TextUtils.isEmpty(G42.getText().toString()) || TextUtils.isEmpty(G44.getText().toString())){
                    new AlertDialog.Builder(Puzzle1.this)
                            .setTitle("Submission Denied!!")
                            .setMessage("Please complete the puzzle")
                            .setNegativeButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle1.this);
                    alert.setTitle("Congratulations");
                    alert.setMessage("You have completed level 1 \n20 Points \n");
                    alert.setIcon(R.drawable.congratulations_icon);
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            fStore.collection("userDetails").document(fAuth.getCurrentUser().getUid()).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String score = documentSnapshot.getString("Score");
                                            int score1 = Integer.parseInt(score) + 20;
                                            fStore.collection("userDetails").document(fAuth.getCurrentUser().getUid()).update(
                                                    "Score", String.valueOf(score1)
                                            );
                                            Intent intent = new Intent(Puzzle1.this, puzzlelevels.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                        }
                    });
                    alert.show();
                }
            }
        });

        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle1.this);

                alert.setTitle("Level 1");
                alert.setMessage("Enter your ans");

                final EditText input = new EditText(Puzzle1.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = String.valueOf(input.getText()).toUpperCase();

                        if (value.equals("LOOP")){
                            G12.setText("O");
                            G13.setText("O");
                        }else if(value.equals("LINE")){
                            G21.setText("I");
                            G31.setText("N");
                        }else if(value.equals("EDGE")){
                            G42.setText("D");
                            G44.setText("E");
                        }else{
                            new AlertDialog.Builder(Puzzle1.this)
                                    .setTitle("Wrong Ans")
                                    .setMessage("Please try again")
                                    .setNegativeButton("Ok", null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                        // Do something with value!
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alert.show();
            }
        });
    }
}
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Puzzle5 extends AppCompatActivity {

    TextView G22, G32, G33, G35, G36, G37, G42, G44, G54, G74, G84, G94;
    AppCompatButton ans,submit;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle5);

        G22  = findViewById(R.id.G22);
        G32 = findViewById(R.id.G32);
        G33 = findViewById(R.id.G33);
        G35 = findViewById(R.id.G35);
        G36 = findViewById(R.id.G36);
        G37 = findViewById(R.id.G37);
        G42 = findViewById(R.id.G42);
        G44 = findViewById(R.id.G44);
        G54 = findViewById(R.id.G54);
        G74 = findViewById(R.id.G74);
        G84 = findViewById(R.id.G84);
        G94 = findViewById(R.id.G94);

        ans = findViewById(R.id.ans);
        submit = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(G22.getText().toString()) || TextUtils.isEmpty(G33.getText().toString()) || TextUtils.isEmpty(G74.getText().toString())){
                    new AlertDialog.Builder(Puzzle5.this)
                            .setTitle("Submission Denied!!")
                            .setMessage("Please complete the puzzle")
                            .setNegativeButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle5.this);
                    alert.setTitle("Congratulations");
                    alert.setMessage("You have completed level 5 \n 20 Points \n");
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
                                            Intent intent = new Intent(Puzzle5.this, puzzlelevels.class);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle5.this);

                alert.setTitle("Level 4");
                alert.setMessage("Enter your ans");

                final EditText input = new EditText(Puzzle5.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = String.valueOf(input.getText()).toUpperCase();

                        if (value.equals("DIJKSTRA")){
                            G32.setText("I");
                            G33.setText("J");
                            G35.setText("S");
                            G36.setText("T");
                            G37.setText("R");
                        }else if(value.equals("PRIMS")){
                            G22.setText("R");
                            G32.setText("I");
                            G42.setText("M");
                        }else if(value.equals("KRUSKAL")){
                            G44.setText("R");
                            G54.setText("U");
                            G74.setText("K");
                            G84.setText("A");
                            G94.setText("L");
                        } else{
                            new AlertDialog.Builder(Puzzle5.this)
                                    .setTitle("Wrong Ans")
                                    .setMessage("Please try again")
                                    .setNegativeButton("Ok", null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
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
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

public class Puzzle4 extends AppCompatActivity {

    TextView G26, G32, G33, G35, G37, G38, G42, G46, G52, G56, G64, G72, G73, G75, G76, G84;
    AppCompatButton ans,submit;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle4);

        G26 = findViewById(R.id.G26);
        G32 = findViewById(R.id.G32);
        G33 = findViewById(R.id.G33);
        G35 = findViewById(R.id.G35);
        G37 = findViewById(R.id.G37);
        G38 = findViewById(R.id.G38);
        G42 = findViewById(R.id.G42);
        G46 = findViewById(R.id.G46);
        G52 = findViewById(R.id.G52);
        G56 = findViewById(R.id.G56);
        G64 = findViewById(R.id.G64);
        G72 = findViewById(R.id.G72);
        G73 = findViewById(R.id.G73);
        G75 = findViewById(R.id.G75);
        G76 = findViewById(R.id.G76);
        G84 = findViewById(R.id.G84);

        ans = findViewById(R.id.ans);
        submit = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(G26.getText().toString()) || TextUtils.isEmpty(G33.getText().toString()) ||
                        TextUtils.isEmpty(G52.getText().toString()) || TextUtils.isEmpty(G75.getText().toString()) ||
                        TextUtils.isEmpty(G84.getText().toString()) ){
                    new AlertDialog.Builder(Puzzle4.this)
                            .setTitle("Submission Denied!!")
                            .setMessage("Please complete the puzzle")
                            .setNegativeButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle4.this);
                    alert.setTitle("Congratulations");
                    alert.setMessage("You have completed level 4 \n 20 Points \n");
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
                                            Intent intent = new Intent(Puzzle4.this, puzzlelevels.class);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle4.this);

                alert.setTitle("Level 4");
                alert.setMessage("Enter your ans");

                final EditText input = new EditText(Puzzle4.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = String.valueOf(input.getText()).toUpperCase();

                        if (value.equals("OPENWALK") || value.equals("OPEN WALK")){
                            G32.setText("P");
                            G33.setText("E");
                            G35.setText("W");
                            G37.setText("L");
                            G38.setText("K");
                        }else if(value.equals("PATH")){
                            G42.setText("A");
                            G52.setText("T");
                        }else if(value.equals("CIRCUIT")){
                            G72.setText("I");
                            G73.setText("R");
                            G75.setText("U");
                            G76.setText("I");
                        } else if(value.equals("CYCLE")) {
                            G64.setText("Y");
                            G84.setText("L");
                        } else if(value.equals("TRAIL")) {
                            G26.setText("R");
                            G46.setText("I");
                            G56.setText("L");
                        }else{
                            new AlertDialog.Builder(Puzzle4.this)
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
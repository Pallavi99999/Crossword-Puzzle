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

public class Puzzle2 extends AppCompatActivity {

    TextView G18, G21, G22, G23, G24, G25, G26, G27, G28, G29, G38, G48, G58, G68,
            G78, G36, G46, G56, G66, G76, G77, G86, G75, G74, G73, G72, G71;
    AppCompatButton ans,submit;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle2);

        ans = findViewById(R.id.ans);
        submit = findViewById(R.id.submit);
        G18 = findViewById(R.id.G18);
        G21 = findViewById(R.id.G21);
        G22 = findViewById(R.id.G22);
        G23 = findViewById(R.id.G23);
        G24 = findViewById(R.id.G24);
        G25 = findViewById(R.id.G25);
        G26 = findViewById(R.id.G26);
        G27 = findViewById(R.id.G27);
        G28 = findViewById(R.id.G28);
        G29 = findViewById(R.id.G29);
        G38 = findViewById(R.id.G38);
        G48 = findViewById(R.id.G48);
        G58 = findViewById(R.id.G58);
        G68 = findViewById(R.id.G68);
        G78 = findViewById(R.id.G78);
        G36 = findViewById(R.id.G36);
        G46 = findViewById(R.id.G46);
        G56 = findViewById(R.id.G56);
        G66 = findViewById(R.id.G66);
        G76 = findViewById(R.id.G76);
        G77 = findViewById(R.id.G77);
        G86 = findViewById(R.id.G86);
        G75 = findViewById(R.id.G75);
        G74 = findViewById(R.id.G74);
        G73 = findViewById(R.id.G73);
        G72 = findViewById(R.id.G72);
        G71 = findViewById(R.id.G71);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(G18.getText().toString()) || TextUtils.isEmpty(G21.getText().toString()) || TextUtils.isEmpty(G22.getText().toString()) ||
                        TextUtils.isEmpty(G23.getText().toString()) || TextUtils.isEmpty(G24.getText().toString()) || TextUtils.isEmpty(G25.getText().toString()) ||
                        TextUtils.isEmpty(G26.getText().toString()) || TextUtils.isEmpty(G27.getText().toString()) || TextUtils.isEmpty(G28.getText().toString()) ||
                        TextUtils.isEmpty(G29.getText().toString()) || TextUtils.isEmpty(G38.getText().toString()) || TextUtils.isEmpty(G48.getText().toString()) ||
                        TextUtils.isEmpty(G58.getText().toString()) || TextUtils.isEmpty(G68.getText().toString()) || TextUtils.isEmpty(G78.getText().toString()) ||
                        TextUtils.isEmpty(G36.getText().toString()) || TextUtils.isEmpty(G46.getText().toString()) || TextUtils.isEmpty(G56.getText().toString()) ||
                        TextUtils.isEmpty(G66.getText().toString()) || TextUtils.isEmpty(G76.getText().toString()) || TextUtils.isEmpty(G77.getText().toString()) ||
                        TextUtils.isEmpty(G86.getText().toString()) || TextUtils.isEmpty(G75.getText().toString()) || TextUtils.isEmpty(G74.getText().toString()) ||
                        TextUtils.isEmpty(G73.getText().toString()) || TextUtils.isEmpty(G72.getText().toString()) || TextUtils.isEmpty(G71.getText().toString())){
                    new AlertDialog.Builder(Puzzle2.this)
                            .setTitle("Submission Denied!!")
                            .setMessage("Please complete the puzzle")
                            .setNegativeButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle2.this);
                    alert.setTitle("Congratulations");
                    alert.setMessage("You have completed level 2 \n 20 Points \n");
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
                                            Intent intent = new Intent(Puzzle2.this, puzzlelevels.class);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle2.this);

                alert.setTitle("Level 2");
                alert.setMessage("Enter your ans");

                final EditText input = new EditText(Puzzle2.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = String.valueOf(input.getText()).toUpperCase();

                        if (value.equals("CHEMISTRY")){
                            G22.setText("H");
                            G23.setText("E");
                            G24.setText("M");
                            G25.setText("I");
                            G27.setText("T");
                            G28.setText("R");
                            G29.setText("Y");
                        }else if(value.equals("SCIENCE")){
                            G36.setText("C");
                            G46.setText("I");
                            G56.setText("E");
                            G66.setText("N");
                            G86.setText("E");
                        }else if(value.equals("PHYSICS")){
                            G72.setText("H");
                            G73.setText("Y");
                            G74.setText("S");
                            G75.setText("I");
                            G77.setText("S");
                        } else if(value.equals("NETWORK")) {
                            G68.setText("E");
                            G58.setText("T");
                            G48.setText("W");
                            G38.setText("O");
                            G28.setText("R");
                        }else{
                            new AlertDialog.Builder(Puzzle2.this)
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
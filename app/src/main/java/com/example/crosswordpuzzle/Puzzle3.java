package com.example.crosswordpuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Puzzle3 extends AppCompatActivity {

    TextView G22, G24, G26, G28, G33, G35, G37, G39, G43, G45, G47, G49, G55, G57, G59, G65, G67, G75, G77, G79, G85, G89, G99;
    AppCompatButton ans,submit;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle3);

        G22 = findViewById(R.id.G22);
        G24 = findViewById(R.id.G24);
        G26 = findViewById(R.id.G26);
        G28 = findViewById(R.id.G28);
        G33 = findViewById(R.id.G33);
        G35 = findViewById(R.id.G35);
        G37 = findViewById(R.id.G37);
        G39 = findViewById(R.id.G39);
        G43 = findViewById(R.id.G43);
        G45 = findViewById(R.id.G45);
        G47 = findViewById(R.id.G47);
        G49 = findViewById(R.id.G49);
        G55 = findViewById(R.id.G55);
        G57 = findViewById(R.id.G57);
        G59 = findViewById(R.id.G59);
        G65 = findViewById(R.id.G65);
        G67 = findViewById(R.id.G67);
        G75 = findViewById(R.id.G75);
        G77 = findViewById(R.id.G77);
        G79 = findViewById(R.id.G79);
        G85 = findViewById(R.id.G85);
        G89 = findViewById(R.id.G89);
        G99 = findViewById(R.id.G99);

        ans = findViewById(R.id.ans);
        submit = findViewById(R.id.submit);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(G24.getText().toString()) || TextUtils.isEmpty(G45.getText().toString()) ||
                        TextUtils.isEmpty(G57.getText().toString()) || TextUtils.isEmpty(G89.getText().toString())){
                    new AlertDialog.Builder(Puzzle3.this)
                            .setTitle("Submission Denied!!")
                            .setMessage("Please complete the puzzle")
                            .setNegativeButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle3.this);
                    alert.setTitle("Congratulations");
                    alert.setMessage("You have completed level 3 \n 20 Points \n");
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
                                            Intent intent = new Intent(Puzzle3.this, puzzlelevels.class);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(Puzzle3.this);

                alert.setTitle("Level 3");
                alert.setMessage("Enter your ans");

                final EditText input = new EditText(Puzzle3.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = String.valueOf(input.getText()).toUpperCase();

                        if (value.equals("CONNECTED")){
                            G22.setText("O");
                            G24.setText("N");
                            G26.setText("C");
                            G28.setText("E");
                        }else if(value.equals("NULL")){
                            G33.setText("U");
                            G43.setText("L");
                        }else if(value.equals("WEIGHTED")){
                            G35.setText("I");
                            G45.setText("G");
                            G55.setText("H");
                            G65.setText("T");
                            G75.setText("E");
                            G85.setText("D");
                        } else if(value.equals("TRIVIAL")) {
                            G37.setText("R");
                            G47.setText("I");
                            G57.setText("V");
                            G67.setText("I");
                            G77.setText("A");
                        } else if(value.equals("DIRECTED")) {
                            G39.setText("I");
                            G49.setText("R");
                            G59.setText("E");
                            G79.setText("T");
                            G89.setText("E");
                            G99.setText("D");
                        }else{
                            new AlertDialog.Builder(Puzzle3.this)
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
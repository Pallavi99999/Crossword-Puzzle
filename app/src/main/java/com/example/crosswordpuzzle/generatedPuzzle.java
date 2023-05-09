package com.example.crosswordpuzzle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class generatedPuzzle extends AppCompatActivity {

    private static char[][] matrix;
    private static boolean[][] used;
    private static List<String> words;
    private static int rows, cols;
    TextView G11, G12, G13, G14, G15, G16, G21, G22, G23, G24, G25, G26, G31;
    TextView G32, G33, G34, G35, G36, G41, G42, G43, G44, G45, G46, G51, G52;
    TextView G53, G54, G55, G56, G61, G62, G63, G64, G65, G66;
    TextView word11, word12, word13, word14;
    private Context mContext;

    AppCompatButton print;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_puzzle);

//        print = findViewById(R.id.print);
        ConstraintLayout constraintLayout = findViewById(R.id.constraint);

//        print.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                convertToPdf();
//            }
//        });

        G11 = findViewById(R.id.G11);
        G12 = findViewById(R.id.G12);
        G13 = findViewById(R.id.G13);
        G14 = findViewById(R.id.G14);
        G15 = findViewById(R.id.G15);
        G16 = findViewById(R.id.G16);
        G21 = findViewById(R.id.G21);
        G22 = findViewById(R.id.G22);
        G23 = findViewById(R.id.G23);
        G24 = findViewById(R.id.G24);
        G25 = findViewById(R.id.G25);
        G26 = findViewById(R.id.G26);
        G31 = findViewById(R.id.G31);
        G32 = findViewById(R.id.G32);
        G33 = findViewById(R.id.G33);
        G34 = findViewById(R.id.G34);
        G35 = findViewById(R.id.G35);
        G36 = findViewById(R.id.G36);
        G41 = findViewById(R.id.G41);
        G42 = findViewById(R.id.G42);
        G43 = findViewById(R.id.G43);
        G44 = findViewById(R.id.G44);
        G45 = findViewById(R.id.G45);
        G46 = findViewById(R.id.G46);
        G51 = findViewById(R.id.G51);
        G52 = findViewById(R.id.G52);
        G53 = findViewById(R.id.G53);
        G54 = findViewById(R.id.G54);
        G55 = findViewById(R.id.G55);
        G56 = findViewById(R.id.G56);
        G61 = findViewById(R.id.G61);
        G62 = findViewById(R.id.G62);
        G63 = findViewById(R.id.G63);
        G64 = findViewById(R.id.G64);
        G65 = findViewById(R.id.G65);
        G66 = findViewById(R.id.G66);

        word11 = findViewById(R.id.word11);
        word12 = findViewById(R.id.word12);
        word13 = findViewById(R.id.word13);
        word14 = findViewById(R.id.word14);

        Intent intent = getIntent();
        int row = Integer.parseInt(intent.getStringExtra("row"));
        int col = Integer.parseInt(intent.getStringExtra("col"));

        word11.setText("1. " + intent.getStringExtra("word1"));
        word12.setText("2. " + intent.getStringExtra("word2"));
        word13.setText("3. " + intent.getStringExtra("word3"));
        word14.setText("4. " + intent.getStringExtra("word4"));

        if (row == 4){
            G15.setVisibility(View.INVISIBLE);
            G16.setVisibility(View.INVISIBLE);
            G25.setVisibility(View.INVISIBLE);
            G26.setVisibility(View.INVISIBLE);
            G35.setVisibility(View.INVISIBLE);
            G36.setVisibility(View.INVISIBLE);
            G45.setVisibility(View.INVISIBLE);
            G46.setVisibility(View.INVISIBLE);
            G51.setVisibility(View.INVISIBLE);
            G52.setVisibility(View.INVISIBLE);
            G53.setVisibility(View.INVISIBLE);
            G54.setVisibility(View.INVISIBLE);
            G55.setVisibility(View.INVISIBLE);
            G56.setVisibility(View.INVISIBLE);
            G61.setVisibility(View.INVISIBLE);
            G62.setVisibility(View.INVISIBLE);
            G63.setVisibility(View.INVISIBLE);
            G64.setVisibility(View.INVISIBLE);
            G65.setVisibility(View.INVISIBLE);
            G66.setVisibility(View.INVISIBLE);
        } else if (row == 5){
            G16.setVisibility(View.INVISIBLE);
            G26.setVisibility(View.INVISIBLE);
            G36.setVisibility(View.INVISIBLE);
            G46.setVisibility(View.INVISIBLE);
            G56.setVisibility(View.INVISIBLE);
            G61.setVisibility(View.INVISIBLE);
            G62.setVisibility(View.INVISIBLE);
            G63.setVisibility(View.INVISIBLE);
            G64.setVisibility(View.INVISIBLE);
            G65.setVisibility(View.INVISIBLE);
            G66.setVisibility(View.INVISIBLE);
        }

        Log.d("puzz", row + " ");

        String[] words = {intent.getStringExtra("word1"), intent.getStringExtra("word2"), intent.getStringExtra("word3"), intent.getStringExtra("word4")};

        char[][] puzzle = generate(row, col, words);
        if (puzzle != null) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (i==0 && j==0){
                        G11.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G11.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==0 && j==1){
                        G12.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G12.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==0 && j==2){
                        G13.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G13.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==0 && j==3){
                        G14.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G14.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==0 && j==4){
                        G15.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G15.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==0 && j==5){
                        G16.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G16.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==0){
                        G21.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G21.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==1){
                        G22.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G22.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==2){
                        G23.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G23.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==3){
                        G24.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G24.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==4){
                        G25.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G25.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==1 && j==5){
                        G26.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G26.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==0){
                        G31.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G31.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==1){
                        G32.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G32.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==2){
                        G33.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G33.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==3){
                        G34.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G34.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==4){
                        G35.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G35.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==2 && j==5){
                        G36.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G36.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==0){
                        G41.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G41.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==1){
                        G42.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G42.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==2){
                        G43.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G43.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==3){
                        G44.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G44.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==4){
                        G45.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G45.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==3 && j==5){
                        G46.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G46.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==0){
                        G51.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G51.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==1){
                        G52.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G52.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==2){
                        G53.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G53.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==3){
                        G54.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G54.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==4){
                        G55.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G55.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==4 && j==5){
                        G56.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G56.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==0){
                        G61.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G61.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==1){
                        G62.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G62.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==2){
                        G63.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G63.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==3){
                        G64.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G64.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==4){
                        G65.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G65.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }
                    else if (i==5 && j==5) {
                        G66.setText(String.valueOf(puzzle[i][j]));
                        if (puzzle[i][j] == '\0'){
                            G66.setBackgroundColor(Color.parseColor("#D9D9D9"));
                        }
                    }

                    Log.d("puzz", String.valueOf(puzzle[i][j]));
                    System.out.print(puzzle[i][j] + " ");
                }
                System.out.println();
            }
        }else{
            Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public static char[][] generate(int r, int c, String[] w) {
        rows = r;
        cols = c;
        matrix = new char[rows][cols];
        used = new boolean[rows][cols];
        words = new ArrayList<>(Arrays.asList(w));
        Collections.shuffle(words);

        // place the first word horizontally in the center of the matrix
        String word = words.remove(0);
        int row = rows/2, col = (cols-word.length())/2;
        for (int i = 0; i < word.length(); i++) {
            matrix[row][col+i] = word.charAt(i);
            used[row][col+i] = true;
        }

        // try to place the remaining words using backtracking
        if (!solve(0)) {
            System.out.println("Unable to generate crossword puzzle.");
            return null;
        }

        return matrix;
    }

    private static boolean solve(int index) {
        // base case: all words have been placed
        if (index == words.size()) {
            return true;
        }

        String word = words.get(index);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!used[i][j]) {
                    // try to place the word horizontally
                    if (canPlace(word, i, j, 1)) {
                        placeWord(word, i, j, 1);
                        if (solve(index+1)) {
                            return true;
                        }
                        removeWord(word, i, j, 1);
                    }

                    // try to place the word vertically
                    if (canPlace(word, i, j, 0)) {
                        placeWord(word, i, j, 0);
                        if (solve(index+1)) {
                            return true;
                        }
                        removeWord(word, i, j, 0);
                    }
                }
            }
        }

        return false;
    }

    private static boolean canPlace(String word, int row, int col, int dir) {
        if (dir == 1 && col+word.length() > cols) {
            return false;
        }
        if (dir == 0 && row+word.length() > rows) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (dir == 1 && used[row][col+i] && matrix[row][col+i] != word.charAt(i)) {
                return false;
            }
            if (dir == 0 && used[row+i][col] && matrix[row+i][col] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static void placeWord(String word, int row, int col, int dir) {
        for (int i = 0; i < word.length(); i++) {
            if (dir == 1) {
                matrix[row][col+i] = word.charAt(i);
                used[row][col+i] = true;
            } else {
                matrix[row+i][col] = word.charAt(i);
                used[row+i][col] = true;
            }
        }
    }

    private static void removeWord(String word, int row, int col, int dir) {
        for (int i = 0; i < word.length(); i++) {
            if (dir == 1) {
                matrix[row][col+i] = '\0';
                used[row][col+i] = false;
            } else {
                matrix[row+i][col] = '\0';
                used[row+i][col] = false;
            }
        }
    }

//    private void convertToPdf(){
//        PdfGenerator pdfGenerator = new PdfGenerator(generatedPuzzle.this);
//        // llLayoutToPdfMain is variable of that view which convert to PDF
//        Bitmap bitmap = pdfGenerator.getViewScreenShot();
//        pdfGenerator.saveImageToPDF(llLayoutToPdfMain, bitmap);
//    }

//    public class PdfGenerator {
//        private static String TAG= PdfGenerator.class.getSimpleName();
//        private File mFile;
//        private Context mContext;
//
//        public PdfGenerator(Context context) {
//            this.mContext = context;
//        }
//
//        /*save image to pdf*/
//        public void saveImageToPDF(View title, Bitmap bitmap) {
//            File path = Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOCUMENTS);
//            if(!path.exists()) {
//                path.mkdirs();
//            }
//            try {
//                mFile = new File(path + "/", System.currentTimeMillis() + ".pdf");
//                if (!mFile.exists()) {
//                    int height = bitmap.getHeight();
//                    PdfDocument document = new PdfDocument();
//                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), height, 1).create();
//                    PdfDocument.Page page = document.startPage(pageInfo);
//                    Canvas canvas = page.getCanvas();
//                    title.draw(canvas);
//                    canvas.drawBitmap(bitmap, null, new Rect(0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight()), null);
//                    document.finishPage(page);
//                    try {
//                        mFile.createNewFile();
//                        OutputStream out = new FileOutputStream(mFile);
//                        document.writeTo(out);
//                        document.close();
//                        out.close();
//                        Log.e(TAG,"Pdf Saved at:"+mFile.getAbsolutePath());
//                        Toast.makeText(mContext,"Pdf Saved at:"+mFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        /*method for generating bitmap from LinearLayout, RelativeLayout etc.*/
//        public Bitmap getViewScreenShot(View view)
//        {
//            view.setDrawingCacheEnabled(true);
//            view.buildDrawingCache();
//            Bitmap bm = view.getDrawingCache();
//            return bm;
//        }
//
//
//        /*method for generating bitmap from ScrollView, NestedScrollView*/
//        public Bitmap getScrollViewScreenShot(ScrollView nestedScrollView)
//        {
//
//            int totalHeight = nestedScrollView.getChildAt(0).getHeight();
//            int totalWidth = nestedScrollView.getChildAt(0).getWidth();
//            return getBitmapFromView(nestedScrollView,totalHeight,totalWidth);
//        }
//
//
//        public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {
//
//            Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(returnedBitmap);
//            Drawable bgDrawable = view.getBackground();
//            if (bgDrawable != null)
//                bgDrawable.draw(canvas);
//            else
//                canvas.drawColor(Color.WHITE);
//            view.draw(canvas);
//            return returnedBitmap;
//        }
//    }

//    private boolean checkPermissionGranted(){
//        if((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//                && (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
//            // Permission has already been granted
//            return  true;
//        } else {
//            return false;
//        }
//    }
//
//    private void requestPermission(){
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//    }


}

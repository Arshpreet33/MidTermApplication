package com.example.midtermapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4;  //To initialize the Buttons variables.
    TextView txtScore;  //To initialize a textView variable.
    TextView txtHighScore;  //To initialize a textView variable.
    LinearLayout layoutMain; //To initialize a linearLayout variable.

    int score = 0;  //I took this variable to initialize the Main Score and use it in the application.
    int highScore = 0;

    int btn1Clicks = 0; //To initialize a Number of clicks of Button 1 .
    int btn2Clicks = 0; //To initialize a Number of clicks of Button 2 .
    int btn3Clicks = 0; //To initialize a Number of clicks of Button 3 .

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);   //Identifying the Layout connected to this Activity java class.

        //Connecting Button variables with Buttons on the View(Layout).
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        //Connecting textView variable with TextView on the View(Layout).
        txtScore = findViewById(R.id.txtScore);
        txtHighScore = findViewById(R.id.txtHighScore);

        //Connecting linearLayout variable with LinearLayout on the View(Layout).
        layoutMain = findViewById(R.id.layoutMain);

        //Applying OnClick events on the button variables.
        // 'this' means that OnClick events will be handled by the public method 'onClick'
        // implemented by 'View.OnClickListener' Interface.
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        sp = getSharedPreferences(MyVariables.fileName, Context.MODE_PRIVATE);

        txtHighScore.setText(Integer.toString(sp.getInt(MyVariables.highScore, MyVariables.defaultHighScore)));
    }

    //  The public method 'onClick' implemented by 'View.OnClickListener' Interface.
    @Override
    public void onClick(View v) {

        //Switch statement to get the control of the button which is clicked.
        switch (v.getId()) {
            case R.id.btn1: // Button 1 is clicked
                btn1Click();//Call private method
                break;
            case R.id.btn2: // Button 2 is clicked
                btn2Click();//Call private method
                break;
            case R.id.btn3: // Button 3 is clicked
                btn3Click();//Call private method
                break;
            case R.id.btn4: // Button 4 is clicked
                btn4Click();//Call private method
                break;
            default:
                Toast.makeText(this, "Wrong Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void btn1Click() {
        btn1Clicks++;
        score++;
        txtScore.setText(Integer.toString(score));

        ColorDrawable colorDrawable = (ColorDrawable) btn1.getBackground();

        //To check if Button 1 color is already Red, then check if number of Button1 clicks are even, change to odd
        if (colorDrawable.getColor() == Color.RED) {
            if (btn1Clicks % 2 == 0) {
                btn1Clicks++;
            }
        }
        //To check if Button 1 color is already Green, then check if number of Button1 clicks are odd, change to even
        if (colorDrawable.getColor() == Color.GREEN) {
            if (btn1Clicks % 2 != 0) {
                btn1Clicks++;
            }
        }

        if (btn1Clicks % 2 == 0) {  // Number of clicks on Button1 are Even
            btn1.setBackgroundColor(Color.RED);//Set background color of Button 1 to red
        } else {  // Number of clicks on Button1 are Even
            btn1.setBackgroundColor(Color.GREEN);//Set background color of Button 1 to green
        }
    }

    private void btn2Click() {
        btn2Clicks++;
        score = score + 10;
        txtScore.setText(Integer.toString(score));

        if (btn2Clicks % 2 == 0) {  // Number of clicks on Button1 are Even
            layoutMain.setBackgroundColor(Color.BLACK);//Set background color of Main Layout to black
        } else {  // Number of clicks on Button1 are Even
            layoutMain.setBackgroundColor(Color.YELLOW);//Set background color of Main Layout to yellow
        }
    }

    private void btn3Click() {
        btn3Clicks++;
        score = score + 100;
        txtScore.setText(Integer.toString(score));

        //Checking the remainder by dividing the number of Button3 clicks by 4.
        //If remainder is 1
        if (btn3Clicks % 4 == 1) {
            btn1.setBackgroundColor(Color.RED);
            btn2.setBackgroundColor(Color.RED);
            btn3.setBackgroundColor(Color.RED);
            btn4.setBackgroundColor(Color.RED);
        } else if (btn3Clicks % 4 == 2) { //If remainder is 2
            btn1.setBackgroundColor(Color.GREEN);
            btn2.setBackgroundColor(Color.GREEN);
            btn3.setBackgroundColor(Color.GREEN);
            btn4.setBackgroundColor(Color.GREEN);
        } else if (btn3Clicks % 4 == 3) { //If remainder is 3
            btn1.setBackgroundColor(Color.YELLOW);
            btn2.setBackgroundColor(Color.YELLOW);
            btn3.setBackgroundColor(Color.YELLOW);
            btn4.setBackgroundColor(Color.YELLOW);
        } else { //If remainder is 0
            btn1.setBackgroundColor(Color.BLACK);
            btn2.setBackgroundColor(Color.BLACK);
            btn3.setBackgroundColor(Color.BLACK);
            btn4.setBackgroundColor(Color.BLACK);
        }
    }

    private void btn4Click() {

        ColorDrawable colorDrawable = (ColorDrawable) btn1.getBackground();

        //Set the bridge start and end
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        //Add parameters
        intent.putExtra("keyScore", score);
        intent.putExtra("keyColor", colorDrawable.getColor());
        //Go to activity2
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (score > highScore) {

            editor = sp.edit();
            editor.putInt(MyVariables.highScore, score);
            editor.apply();
        }
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (score > highScore) {

            editor = sp.edit();
            editor.putInt(MyVariables.highScore, score);
            editor.apply();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (score > highScore) {

            editor = sp.edit();
            editor.putInt(MyVariables.highScore, score);
            editor.apply();
        }
    }

 */
}

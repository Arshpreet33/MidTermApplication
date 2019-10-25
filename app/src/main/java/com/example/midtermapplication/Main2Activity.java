package com.example.midtermapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnAC2;  //To initialize the Buttons variables.
    TextView txtScore2;  //To initialize a textView variable.
    LinearLayout layoutMain2; //To initialize a linearLayout variable.


    Intent intent;//To initialize a Intent variable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);   //Identifying the Layout connected to this Activity java class.

        //Retrieving integer values of Intent by Get Extras.
        int score = getIntent().getExtras().getInt("keyScore");
        int color = getIntent().getExtras().getInt("keyColor");

        //Connecting textView variable with TextView on the View(Layout).
        txtScore2 = findViewById(R.id.txtScore2);

        //Connecting Button variables with Buttons on the View(Layout).
        btnAC2 = findViewById(R.id.btnAC2);

        //Connecting linearLayout variable with LinearLayout on the View(Layout).
        layoutMain2 = findViewById(R.id.layoutMain2);

        //Applying OnClick events on the button variables.
        // 'this' means that OnClick events will be handled by the public method 'onClick'
        // implemented by 'View.OnClickListener' Interface.
        btnAC2.setOnClickListener(this);

        //Implement the purpose to make this Activity. ie.
        //Setting the values of Integer variables to score TextView and background color of Layout respectively.
        txtScore2.setText(Integer.toString(score));
        layoutMain2.setBackgroundColor(color);

    }


    //  The public method 'onClick' implemented by 'View.OnClickListener' Interface.
    @Override
    public void onClick(View v) {

        //Switch statement to get the control of the button which is clicked.
        switch (v.getId()) {
            case R.id.btnAC2: // Button is clicked
                btnClick();//Call private method
                break;
            default:
                Toast.makeText(this, "Wrong Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void btnClick() {
        //Going back to Activity 1, instead of closing the application, the user can click here and start the process again.
        //Set the bridge start and end
        intent = new Intent(Main2Activity.this, MainActivity.class);
        //Go to activity1
        startActivity(intent);
    }

}

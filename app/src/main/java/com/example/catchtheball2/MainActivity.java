package com.example.catchtheball2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime;
    TextView textViewScore;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageViewsArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime=(TextView) findViewById(R.id.textViewTime);
        textViewScore=(TextView) findViewById(R.id.textViewScore);
        imageView1=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageViewsArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();



        score=0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText("zaman: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textViewTime.setText("zaman bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageViewsArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setCancelable(false);

                alert.setTitle("restart ? ");
                alert.setMessage("Yeniden oynacak mısın");
                alert.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);

                    }
                });

                alert.show();


            }
        }.start();
    }

    public void skoruArttır(View View){
        score++;
        textViewScore.setText("puan: " + score);
    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageViewsArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int rastgeleSayi=random.nextInt(9);
                imageViewsArray[rastgeleSayi].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);


    }
}
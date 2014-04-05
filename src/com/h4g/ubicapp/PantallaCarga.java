package com.h4g.ubicapp;

import java.util.Timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;

public class PantallaCarga extends Activity {
    Context context;
    ProgressBar barraprogreso;
    int i=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_carga);
		context = getApplicationContext();
		barraprogreso = new ProgressBar(context);
		barraprogreso = (ProgressBar) findViewById(R.id.pantallacargabarraprogreso);
		
		
		CountDownTimer timer = new CountDownTimer(6000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
				barraprogreso.setProgress(i);
				i=i+10;
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,MenuPrincipal.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}

	

}

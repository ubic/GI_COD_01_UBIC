package com.h4g.ubicapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityMore extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent (this,MenuPrincipal.class));
		//finish();
		
	}
	
}

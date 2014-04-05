package com.h4g.ubicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ActivityMenuSecundario extends Activity {
Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_secundario);
		context = getApplicationContext();
	}
	public void aparcamientos(View v){
		Toast.makeText(context, "WORKING IN PROGRESS", Toast.LENGTH_LONG).show();
	}
	public void hoteles(View v){
		Toast.makeText(context, "WORKING IN PROGRESS", Toast.LENGTH_LONG).show();
	}
	public void restaurantes(View v){
		String url="http://ubic.github.io/GI_COD_01_UBIC/";
				Intent i=new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	public void more(View v){
		startActivity(new Intent(this,ActivityMore.class));
		//finish();
	}
}

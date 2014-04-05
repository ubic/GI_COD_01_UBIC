package com.h4g.ubicapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class MenuPrincipal extends Activity {
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		context = getApplicationContext();
	}
	public void aparcamientos(View v){
		double currentlat = 43.522796;
		double currentlng = -5.611804;
		double destlat = 43.5360372; 
		double destlng =-5.6428152;
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+currentlat+","+currentlng+"&daddr="+destlat+","+destlng));
		startActivity(intent);
	}
	public void hoteles(View v){
		Intent intent = new Intent(context, ActivityHoteles.class);
		//startActivity(new Intent(this,ActivityHoteles.class));
		startActivity(intent);
	}
	public void restaurantes(View v){
		startActivity(new Intent(this,ActivityRestaurantes.class));
		//finish();
	}
	public void more(View v){
		startActivity(new Intent(this,ActivityMenuSecundario.class));
		//finish();
	}
}

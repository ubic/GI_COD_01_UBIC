package com.h4g.ubicapp;

import java.util.ArrayList;
import java.util.Iterator;

import com.h4g.clases.GPSTracker;
import com.h4g.clases.Hotel;
import com.h4g.database.DBHelper;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ActivityHoteles extends FragmentActivity {
	private GoogleMap map;
	private String nombreHotel;
	private double latHotel;
	private double lngHotel;
	private double currentLatitude;
	private double currentLongitude;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitys_servicios);
		context = getApplicationContext();
		
		Hotel hotel1 = new Hotel("Silken Ciudad Gijon",43.537682 ,-5.67474);
		Hotel hotel2 = new Hotel("Sercotel La Boroña",43.50890,-5.69432);
		ArrayList<Hotel> hotelArray = new ArrayList<Hotel>();
        hotelArray.add(hotel1);
        hotelArray.add(hotel2);
		
		
		/*
		DBHelper db = new DBHelper(context);
		db.open();
		ArrayList<Hotel> listaHoteles = db.getAllHotel();
		db.close();
		*/
		/*
		GPSTracker gps = new GPSTracker(this);
		currentLatitude = gps.getLatitude();
		currentLongitude = gps.getLongitude();
		*/
		map = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		Iterator<Hotel> it = hotelArray.iterator();
		while (it.hasNext()){
			Hotel hotel = new Hotel();
			hotel = (Hotel) it.next();
			nombreHotel = hotel.getNombre();
			latHotel = hotel.getLatitud();
			lngHotel = hotel.getLongitud();
			addMarker(latHotel, lngHotel, nombreHotel);
		}
		
		// Llamamos a la funcion para activar el boton de Geolocalización
		myLocation();	
	}
	
	private void addMarker(double lat, double lng, String nombre) {
		//Creamos un objeto que contendra las coordenadas del hotel, que usaremos a la hora de establecer los parametros del enfoque del mapa.
		LatLng coords = new LatLng(lat, lng);
		//Añadimos un marcador.
		map.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
				.title(nombre)
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
		//Establecemos la configuracion de la camara sobre el mapa: coordenadas objetivo y el zoom que se aplicara sobre dichas coordenadas.
		CameraPosition camPos = new CameraPosition.Builder()
				.target(coords)
				.zoom(13).build();
		CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
		map.animateCamera(camUpd);
	}
	
	private void myLocation() { map.setMyLocationEnabled(true); }
	/*
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent (this,MenuPrincipal.class));
		finish();
		
	}
	*/
}

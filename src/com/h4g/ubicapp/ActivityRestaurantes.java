package com.h4g.ubicapp;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.h4g.clases.Hotel;
import com.h4g.clases.Restaurante;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityRestaurantes extends FragmentActivity{
	private GoogleMap map;
	private String nombreRestaurante;
	private double latRestaurante;
	private double lngRestaurante;
	private double currentLatitude;
	private double currentLongitude;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitys_servicios);
		context = getApplicationContext();
		Restaurante hotel1 = new Restaurante(1,"El Puerto",43.5462257 ,-5.664897);
		Restaurante hotel2 = new Restaurante(2,"Coroca",43.5601188,-5.706292399999938);
		ArrayList<Restaurante> hotelArray = new ArrayList<Restaurante>();
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
		Iterator<Restaurante> it = hotelArray.iterator();
		while (it.hasNext()){
			Restaurante hotel = new Restaurante();
			hotel = (Restaurante) it.next();
			nombreRestaurante = hotel.getNombre();
			latRestaurante = hotel.getLatitud();
			lngRestaurante = hotel.getLongitud();
			addMarker(latRestaurante, lngRestaurante, nombreRestaurante);
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

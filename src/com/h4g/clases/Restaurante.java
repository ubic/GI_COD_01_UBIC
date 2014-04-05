package com.h4g.clases;

public class Restaurante {
	private int id;
	private String nombre;
	private double latitud;
	private double longitud;
	
	
	public Restaurante(){}
	public Restaurante(int id, String nombre, double latitud, double longitud) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}


	public int getId() {return id;}
	public String  getNombre() {return nombre;}
	public double getLatitud() {return latitud;}
	public double getLongitud() {return longitud;}
	
	
	public void setId(int id) {this.id = id;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setLatitud(double latitud) {this.latitud = latitud;}
	public void setLongitud(double longitud) {this.longitud = longitud;}
}

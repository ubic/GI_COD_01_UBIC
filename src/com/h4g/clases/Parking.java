package com.h4g.clases;

public class Parking {
	private int id;
	private String direccion;
	private double latitud;
	private double longitud;
	
	
	public Parking(){}
	public Parking(int id, String direccion, double latitud, double longitud) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	
	public int getId() {return id;}
	public String getDireccion() {return direccion;}
	public double getLatitud() {return latitud;}
	public double getLongitud() {return longitud;}
	
	
	public void setId(int id) {this.id = id;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public void setLatitud(double latitud) {this.latitud = latitud;}
	public void setLongitud(double longitud) {this.longitud = longitud;}
}

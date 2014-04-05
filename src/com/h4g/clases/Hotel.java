package com.h4g.clases;

import java.util.ArrayList;

public class Hotel {
	private int id;
	private String nombre;
	private String direccion;
	private double latitud;
	private double longitud;
	
	
	public Hotel() {}
	public Hotel(int id, String nombre, double latitud, double longitud) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Hotel(String nombre, double latitud, double longitud) {
		super();
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	
	public int getId() {return id;}
	public String getNombre() {return nombre;}
	public String getDireccion() {return direccion;}
	public double getLatitud() {return latitud;}
	public double getLongitud() {return longitud;}
	
	
	public void setId(int id) {this.id = id;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public void setLatitud(double latitud) {this.latitud = latitud;}
	public void setLongitud(double longitud) {this.longitud = longitud;}
}

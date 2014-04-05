package com.h4g.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import com.h4g.clases.Hotel;
import com.h4g.clases.Parking;
import com.h4g.clases.Restaurante;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
	private static String DB_PATH = "data/data/com.h4g.ubic/databases/";
	private static String DB_NAME = "ubic.s3db";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	// Creamos las variables correspondientes a los campos de la base de datos
	private static final String KEY_TABLA_PARKING = "parking";
	private static final String KEY_TABLA_HOTELES = "hoteles";
	private static final String KEY_TABLA_RESTAURANTES = "restaurantes";
	private static final String KEY_ID = "_id";
	public final static String KEY_NOMBRE = "nombre";
	public final static String KEY_DIRECCION = "direccion";
	public final static String KEY_LATITUD = "latitud";
	public final static String KEY_LONGITUD = "longitud";
	private static final String[] camposPARK = {"_id","direccion","latitud","longitud"}; // Lo usaremos para pasarle los campos que queremos obtener al realizar la query.
	private static final String[] camposHOT = {"_id","nombre","latitud","longitud"};
	private static final String[] camposRES = {"_id","nombre","latitud","longitud"};
	
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	/*
	 * Comprobamos si la base de datos existe para no copiar el fichero cada vez que se abra la aplicación
	 * Si existe devolveremos true 
	 * Si no existe devolveremos false
	 */
	public boolean checkDataBase() {
		SQLiteDatabase DBcheck = null;
		
		try {
			String myPath = DB_PATH + DB_NAME;
			DBcheck = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e) {
			//Si llegamos aquí es porque la base de datos aún no existe
			throw new Error("La base de datos de existe");
		}
		
		if(DBcheck != null) DBcheck.close();
		return DBcheck != null ? true : false;
	}
	
	/*
	 * Copiamos nuestra base de datos de la carpeta assets a la base de datos creada en el sistema. 
	 */
	public void copyDataBase() throws IOException {
		// Abrimos nuestro fichero de base de datos en modo entrada
		InputStream myFile = myContext.getAssets().open(DB_NAME);
		// Ruta a la base de datos del sistema que acabamos de crear
		String systemDB = DB_PATH + DB_NAME;
		// Abrimos la base de datos del sistema en modo salida
		OutputStream myTarget = new FileOutputStream(systemDB);
		// Hacemos la transferencia de un fichero a otro
		byte[] buffer = new byte[1024];
		int length;
		while((length = myFile.read(buffer))>0){ myTarget.write(buffer, 0, length); }
		// Liberamos los streams
		myTarget.flush();
		myTarget.close();
		myFile.close();
	}
	
	/*
	 * Creamos una base de datos vacia en el sistema y la sobreescribimos con nuestro fichero de base de datos bdBlue.sqlite 
	 */
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		System.err.println(dbExist);
		if (dbExist) {
			this.getReadableDatabase();
			try { copyDataBase(); } 
			catch (IOException e) { throw new Error("Error copiando la base de datos"); }
		}
	}
	
	/*
	 * Creamos y abrimos la base de datos del sistema para operar con ella
	 */
	public void open() throws SQLException {
		try { createDataBase(); }
		catch (IOException e) { throw new Error("No se ha podido crear la base de datos"); }
		
		String myPath = DB_PATH + DB_NAME;
		Log.w("", "PATH ------>" + myPath);
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	/*
	 * Cerramos la base de datos  
	 */
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}
	
	
	/*
	 * Obtenemos los datos de aparcamientos
	 */
	public Parking getParking(int id) {
		Parking parking = new Parking();
		SQLiteDatabase db = myDataBase;
		Cursor result = db.query(KEY_TABLA_PARKING, camposPARK, KEY_ID + "=" + id, null, null, null, null);
		if( result.getCount() >= 1) {
			while(result.moveToNext()) {
				parking.setId(result.getInt(result.getColumnIndex(KEY_ID)));
				parking.setDireccion(result.getString(result.getColumnIndex(KEY_DIRECCION)));
				parking.setLatitud(result.getDouble(result.getColumnIndex(KEY_LATITUD)));
				parking.setLongitud(result.getDouble(result.getColumnIndex(KEY_LONGITUD)));
			}
		}
		return parking;
	}
	
	/*
	 * Obtenemos los datos del hotel que hemos seleccionado
	 */
	public Hotel getHotel(int id) {
		Hotel hotel = new Hotel();
		SQLiteDatabase db = myDataBase;
		Cursor result = db.query(KEY_TABLA_HOTELES, camposHOT, KEY_ID + "=" + id, null, null, null, null);
		if( result.getCount() >= 1) {
			while(result.moveToNext()) {
				hotel.setId(result.getInt(result.getColumnIndex(KEY_ID)));
				hotel.setNombre(result.getString(result.getColumnIndex(KEY_NOMBRE)));
				hotel.setDireccion(result.getString(result.getColumnIndex(KEY_DIRECCION)));
				hotel.setLatitud(result.getDouble(result.getColumnIndex(KEY_LATITUD)));
				hotel.setLongitud(result.getDouble(result.getColumnIndex(KEY_LONGITUD)));
			}
		}
		return hotel;
	}
	/*
	 * Devuelve un listado completo de todos los hoteles 
	 */
	public ArrayList<Hotel> getAllHotel() {
		Hotel hotel = new Hotel();
		ArrayList<Hotel> listaHoteles = new ArrayList<Hotel>();
		SQLiteDatabase db = myDataBase;
		Cursor result = db.query(KEY_TABLA_HOTELES, camposHOT, null, null, null, null, null);
		if( result.getCount() >= 1) {
			while(result.moveToNext()) {
				hotel.setId(result.getInt(result.getColumnIndex(KEY_ID)));
				hotel.setNombre(result.getString(result.getColumnIndex(KEY_NOMBRE)));
				hotel.setLatitud(result.getDouble(result.getColumnIndex(KEY_LATITUD)));
				hotel.setLongitud(result.getDouble(result.getColumnIndex(KEY_LONGITUD)));
				listaHoteles.add(hotel);
			}
		}
		Log.w("", "+++++++++++++++++ TAMAÑO DE LISTAHOTELES" + listaHoteles.size());
		return listaHoteles;
	}
	
	
	/*
	 * Obtenemos los datos de restaurantes
	 */
	public Restaurante getRestaurante(int id) {
		Restaurante restaurante = new Restaurante();
		SQLiteDatabase db = myDataBase;
		Cursor result = db.query(KEY_TABLA_RESTAURANTES, camposRES, KEY_ID + "=" + id, null, null, null, null);
		if( result.getCount() >= 1) {
			while(result.moveToNext()) {
				restaurante.setId(result.getInt(result.getColumnIndex(KEY_ID)));
				restaurante.setNombre(result.getString(result.getColumnIndex(KEY_NOMBRE)));
				restaurante.setLatitud(result.getDouble(result.getColumnIndex(KEY_LATITUD)));
				restaurante.setLongitud(result.getDouble(result.getColumnIndex(KEY_LONGITUD)));
			}
		}
		return restaurante;
	}
	/*
	 * Devuelve un listado completo de todos los restaurantes 
	 */
	public ArrayList<Restaurante> getAllRestaurante() {
		Restaurante restaurante = new Restaurante();
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		SQLiteDatabase db = myDataBase;
		Cursor result = db.query(KEY_TABLA_HOTELES, camposHOT, null, null, null, null, null);
		if( result.getCount() >= 1) {
			while(result.moveToNext()) {
				restaurante.setId(result.getInt(result.getColumnIndex(KEY_ID)));
				restaurante.setNombre(result.getString(result.getColumnIndex(KEY_NOMBRE)));
				restaurante.setLatitud(result.getDouble(result.getColumnIndex(KEY_LATITUD)));
				restaurante.setLongitud(result.getDouble(result.getColumnIndex(KEY_LONGITUD)));
				listaRestaurantes.add(restaurante);
			}
		}
		return listaRestaurantes;
	}
	@Override
	public void onCreate(SQLiteDatabase db) { }
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}

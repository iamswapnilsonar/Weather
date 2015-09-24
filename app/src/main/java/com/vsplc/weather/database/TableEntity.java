package com.vsplc.weather.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TableEntity")
public class TableEntity {

	@DatabaseField(generatedId = true)
	public int _id;

	@DatabaseField(unique = true)
	public String city;

	@DatabaseField
	public String response;

	// Database fields for query..
	public static final String ID = "_id";
	public static final String CITY = "city";
	public static final String RESPONSE = "response";

	public TableEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public TableEntity(String city, String response) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.response = response;
	}
	
	@Override
	public String toString() {
		String divider = "\n -----------------\n";
		String str = "KEY_ID : " + this._id + " CITY : " + this.city
				+ "\n  RESPONSE : " + this.response;
		return divider + str + divider;
	}

}

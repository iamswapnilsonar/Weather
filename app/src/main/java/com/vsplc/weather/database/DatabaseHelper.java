package com.vsplc.weather.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "weather.db";
	private static final int DATABASE_VERSION = 1;
	private Context context;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context; 
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		try {
			Log.i(DatabaseHelper.class.getName(), "onCreating Database..");

			try {
				TableUtils.createTable(connectionSource, TableEntity.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {

		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");

			try {
				TableUtils.dropTable(connectionSource, TableEntity.class, true);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}

			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		super.close();
	}

	/**
	 * Clear all tables and delete database as well.
	 * @return boolean
	 */
	public boolean delete(){
		return context.deleteDatabase(DATABASE_NAME);
	}
}

package com.vsplc.weather.utils;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.vsplc.weather.database.DatabaseHelper;
import com.vsplc.weather.database.TableEntity;
import com.vsplc.weather.web_service.RestClient;

import android.app.Application;
import android.database.SQLException;

public class WeatherApplication extends Application {

	private static RestClient restClient;
	private DatabaseHelper databaseHelper;
	private Dao<TableEntity, Integer> tableEntityDAO;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		restClient = new RestClient();
		databaseHelper = new DatabaseHelper(this);	
	}

	public static RestClient getRestClient() {
		if (null == restClient) {
			restClient = new RestClient();
		}
		return restClient;
	}

	public Dao<TableEntity, Integer> gettableEntityDAO() throws SQLException {

		if (tableEntityDAO == null) {

			try {
				tableEntityDAO = databaseHelper.getDao(TableEntity.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return tableEntityDAO;
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}

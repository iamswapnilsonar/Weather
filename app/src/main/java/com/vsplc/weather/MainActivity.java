package com.vsplc.weather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.vsplc.weather.database.TableEntity;
import com.vsplc.weather.utils.WeatherApplication;
import com.vsplc.weather.web_service.model.Clouds;
import com.vsplc.weather.web_service.model.Coord;
import com.vsplc.weather.web_service.model.Main;
import com.vsplc.weather.web_service.model.Model;
import com.vsplc.weather.web_service.model.Sys;
import com.vsplc.weather.web_service.model.Weather;
import com.vsplc.weather.web_service.model.Wind;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {

	private TextView city, status, humidity, pressure;
	private TextView tvMethod;
	private EditText edtCity;
	private Button btnWeather;
	
	private WeatherApplication application;
	private Context mContext; 
	private Dao<TableEntity, Integer> tableEntityDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = MainActivity.this;
		application = (WeatherApplication) mContext.getApplicationContext();
		tableEntityDAO = application.gettableEntityDAO();
		
		city = (TextView) findViewById(R.id.txt_city);
		status = (TextView) findViewById(R.id.txt_status);
		humidity = (TextView) findViewById(R.id.txt_humidity);
		pressure = (TextView) findViewById(R.id.txt_press);
		tvMethod = (TextView) findViewById(R.id.txt_method);
		edtCity = (EditText) findViewById(R.id.edtSearch);
		btnWeather = (Button) findViewById(R.id.btnWeather);
		btnWeather.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean cancel = false;
		        View focusView = null;
				
				String mCity = edtCity.getText().toString();
				
				// Check for a valid email address.
		        if (TextUtils.isEmpty(mCity)) {
		        	edtCity.setError("This field is required");
		            focusView = edtCity;
		            cancel = true;
		        }

		        if (cancel) {
		            // There was an error; don't attempt login and focus the first
		            // form field with an error.
		            focusView.requestFocus();
		        } else {
		        	getCityWeather(mCity);
		        }
				
			}
		});

	}

	private void getCityWeather(final String cityName) {
        
		try {

//			QueryBuilder<TableEntity, Integer> queryBuilder = tableEntityDAO.queryBuilder();
//			queryBuilder.where().eq(TableEntity.CITY, cityName);
//			PreparedQuery<TableEntity> preparedQuery = queryBuilder.prepare();	
//			List<TableEntity> mCityList = tableEntityDAO.query(preparedQuery);

			
			QueryBuilder<TableEntity, Integer> queryBuilder = tableEntityDAO.queryBuilder();
			queryBuilder.where().like(TableEntity.CITY, "%"+cityName+"%");
			PreparedQuery<TableEntity> preparedQuery = queryBuilder.prepare();
			List<TableEntity> mCityList = tableEntityDAO.query(preparedQuery);
			
			if (mCityList.size() != 0) {
				// we have not found any records in database..
				TableEntity entity = mCityList.get(0);
				Model model = new Gson().fromJson(entity.response, Model.class);
				
				tvMethod.setText("By Database Query");
				city.setText("city :" + model.getName());
				status.setText("Status :" + model.getWeather().get(0).getDescription());
				humidity.setText("humidity :" + model.getMain().getHumidity().toString());
				pressure.setText("pressure :" + model.getMain().getPressure().toString());
				
			}else {
				// fire webservice..
		        WeatherApplication.getRestClient().getRestInterface().getWheatherReport(cityName, new Callback<Model>() {

					@Override
					public void success(Model model, Response response) {

						@SuppressWarnings("unused")
						Coord coord = model.getCoord();
						List<Weather> weatherList = model.getWeather();
						String base = model.getBase();
						Main main = model.getMain();
//						Integer visibility = model.getVisibility();
						Wind wind = model.getWind();
						Clouds clouds = model.getClouds();
//						Integer dt = model.getDt();
						Sys sys = model.getSys();
//						Integer id = model.getId();
						String name = model.getName();
//						Integer cod = model.getCod();

						String jsonResponse = new Gson().toJson(model);
						TableEntity entity = new TableEntity(cityName, jsonResponse);
						
						try {
							tableEntityDAO.create(entity);							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						tvMethod.setText("By Webservice");
						city.setText("city :" + model.getName());
						status.setText("Status :" + model.getWeather().get(0).getDescription());
						humidity.setText("humidity :" + model.getMain().getHumidity().toString());
						pressure.setText("pressure :" + model.getMain().getPressure().toString());
					}

					@Override
					public void failure(RetrofitError error) {
						@SuppressWarnings("unused")
						String merror = error.getMessage();
					}
				});
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

	}
}

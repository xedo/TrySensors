package com.echedeylima.sensores;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SensorsActivity extends Activity {

	private TextView salida;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		salida = (TextView) findViewById(R.id.salida);
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager
				.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : listaSensores)
			log(sensor.getName());

	}

	private void log(String string) {
		salida.append(string + "\n");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

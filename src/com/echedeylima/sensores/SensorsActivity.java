package com.echedeylima.sensores;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SensorsActivity extends Activity implements SensorEventListener {

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
		log ("#############\n#############");

		listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

		if (!listaSensores.isEmpty()) {

			Sensor orientationSensor = listaSensores.get(0);

			sensorManager.registerListener(this, orientationSensor,
					SensorManager.SENSOR_DELAY_UI);
		}

		listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

		if (!listaSensores.isEmpty()) {

			Sensor acelerometerSensor = listaSensores.get(0);

			sensorManager.registerListener(this, acelerometerSensor,
					SensorManager.SENSOR_DELAY_UI);
		}

		listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

		if (!listaSensores.isEmpty()) {

			Sensor magneticSensor = listaSensores.get(0);

			sensorManager.registerListener(this, magneticSensor,
					SensorManager.SENSOR_DELAY_UI);
		}

		listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);

		if (!listaSensores.isEmpty()) {

			Sensor temperatureSensor = listaSensores.get(0);

			sensorManager.registerListener(this, temperatureSensor,
					SensorManager.SENSOR_DELAY_UI);
		}

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

	@Override
	public void onAccuracyChanged(Sensor sensor, int precision) {
	}

	@Override
	public void onSensorChanged(SensorEvent evento) {

		// Cada sensor puede provocar que un thread principal pase por aqu�

		// as� que sincronizamos el acceso (se ver� m�s adelante)

		synchronized (this) {

			switch (evento.sensor.getType()) {

			case Sensor.TYPE_ORIENTATION:

				for (int i = 0; i < 3; i++) {

					log("Orientaci�n " + i + ": " + evento.values[i]);

				}

				break;

			case Sensor.TYPE_ACCELEROMETER:

				for (int i = 0; i < 3; i++) {

					log("Aceler�metro " + i + ": " + evento.values[i]);

				}

				break;

			case Sensor.TYPE_MAGNETIC_FIELD:

				for (int i = 0; i < 3; i++) {

					log("Magnetismo " + i + ": " + evento.values[i]);

				}

				break;

			default:

				for (int i = 0; i < evento.values.length; i++) {

					log("Temperatura " + i + ": " + evento.values[i]);

				}

			}

		}

	}

}

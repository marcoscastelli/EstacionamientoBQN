package uy.com.antel.capacitacion.pruebacursobasico;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


public class servicioDeDescarga extends IntentService {

	public servicioDeDescarga() {
		super("Servicio de descarga");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent ServiceIntent) {
		
		//Servicio que cuenta 10 segundos simulando la descarga de un archivo.
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		//Se obtiene el nombre del archivo a descargar.
		String fileName = ServiceIntent.getExtras().getString("FileName");
		Toast.makeText(servicioDeDescarga.this, fileName, Toast.LENGTH_LONG).show();
		
		//Se guarda el titulo de la notificacion de descarga.
		String title = getResources().getString(R.string.titleFinishDownload);
		
		//Se genera la notificacion.
		NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.a)
		.setTicker(title)
		.setContentTitle(title)
		.setContentText(fileName);
		
		Intent resultIntent = new Intent(this, servicioDeDescarga.class);
		final PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
		Intent.FLAG_ACTIVITY_NEW_TASK);
		nBuilder.setContentIntent(resultPendingIntent);
		NotificationManager nm = (NotificationManager)
		getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(0, nBuilder.build());		
	}

}

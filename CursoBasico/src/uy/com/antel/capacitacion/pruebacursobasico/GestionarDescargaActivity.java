package uy.com.antel.capacitacion.pruebacursobasico;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class GestionarDescargaActivity extends ActionBarActivity {
	String[] namesFiles,sizeFiles;	
	String user;
	Button btnDownload;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestionar_descarga);
		
		//Se guardan los datos
		Bundle bundle=getIntent().getExtras();
		namesFiles = bundle.getStringArray("NamesFiles");
		sizeFiles = bundle.getStringArray("SizeFiles");
		user =  bundle.getString("UserLoged");
		
		//Se setea el titulo de la actividad
        String title=getResources().getString(R.string.title_activity_gestionar_decarga).toString();
        title=title+" "+user;
        setTitle(title);
        
        //Se crea el Spinner.
        Spinner sp = (Spinner) findViewById(R.id.spinnerVL);
		AdaptadorPersonalizado ap =
		new AdaptadorPersonalizado(this, R.layout.personaliza_spinner, namesFiles);
		sp.setAdapter(ap);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(final AdapterView<?> parent, View arg1,
					final int pos, long id) {
				// TODO Auto-generated method stub
				
				//Se obtiene el nombre del archivo seleccionado para descargar.
				final String fileName=(String) parent.getItemAtPosition(pos);
								
				btnDownload=(Button)findViewById(R.id.buttonDownload);
				btnDownload.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Se define el servicio para iniciar la descarga del archivo.
						Intent openServiceIntent = new Intent(GestionarDescargaActivity.this,servicioDeDescarga.class);
						openServiceIntent.putExtra("FileName", fileName);
						Toast t=Toast.makeText(GestionarDescargaActivity.this,getResources().getString(R.string.titleStartDownload),Toast.LENGTH_SHORT);
						t.show();
						startService(openServiceIntent);						
					}
				});			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestionar_decarga, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public class AdaptadorPersonalizado extends ArrayAdapter<String> {
		public AdaptadorPersonalizado(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		}
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// Igual presentacion en el menu que en reposo
		return getView(position, convertView, parent);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflador = getLayoutInflater();
			View vista = inflador.inflate(R.layout.personaliza_spinner, parent, false);
			
			//Se setea la imagen a mostrar en el spinner
			ImageView iv = (ImageView) vista.findViewById(R.id.iconSpinner);
			iv.setImageResource(R.drawable.zip_ic);
			
			//Se cargan los nombres de los archivos en el spinner.
			TextView tvName = (TextView) vista.findViewById(R.id.editTextSpinnerName);
			tvName.setText(namesFiles[position]);
			
			//Se cargan los tamanos de archivos en el spinner
			TextView tvSize = (TextView) vista.findViewById(R.id.editTextSpinnerSize);
			tvSize.setText(sizeFiles[position]);
		return vista;
		}
	}
	
}

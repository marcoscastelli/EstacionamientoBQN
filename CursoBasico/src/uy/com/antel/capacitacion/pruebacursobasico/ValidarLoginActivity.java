package uy.com.antel.capacitacion.pruebacursobasico;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ValidarLoginActivity extends ActionBarActivity {
	String user, pass;		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String dataUser[];
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validar_login);
		
		Bundle bundle=getIntent().getExtras();
		
		dataUser= bundle.getStringArray("dataUser");		
		//se guarda el contenido de los editText.
        user = dataUser[0];
        pass = dataUser[1];
        
        //Se setea el titulo de la actividad
        String title=getResources().getString(R.string.title_activity_validar_login).toString();
        title=title+" "+user;
        setTitle(title);
		
        //Aca se puede hacer un array con los usuarios validos y chequearlos en lugar de lo que hice.
        if((user.equals("USR1")&&pass.equals("USR1"))||(user.equals("USR2")&&pass.equals("USR2"))){
        	String [] dataNamesFiles = {"manual_V.1.1.zip","manual_V.1.2.zip","manual_V.2.1.zip","manual_V.2.2.zip"};
        	String [] dataSizeFiles = {"4,5mb","4,7mb","5,0mb","5,1mb"};
        	Intent returnIntent = new Intent();
        	returnIntent.putExtra("resultNamesFiles",dataNamesFiles);
        	returnIntent.putExtra("resultSizeFiles",dataSizeFiles);
        	returnIntent.putExtra("userLoged",user);
        	setResult(RESULT_OK,returnIntent);
        	finish();        	
        }else{
        	Intent returnIntent = new Intent();
        	setResult(RESULT_CANCELED, returnIntent);
        	finish();        	
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.validar_login, menu);
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
}

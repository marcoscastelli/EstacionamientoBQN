package uy.com.antel.capacitacion.pruebacursobasico;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        //se setea la pantalla por default activity_main.xml 
        setContentView(R.layout.activity_main);
        
        //se define la estructura de datos que identifica al Usuario.
        final EditText etUser = (EditText)findViewById(R.id.editTextUser);
        final EditText etPass = (EditText)findViewById(R.id.editTextPass);
        
        //se muestra el mensaje de bienvenida al sistema.
        final TextView tvWelcome = (TextView)findViewById(R.id.textViewVL);
        tvWelcome.setText(R.string.welcome);
        
        //se define el Boton que lleva a la actividad que valida el login.
        Button btnLogin=(Button)findViewById(R.id.buttonLogin);
        
        btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//se setean los parametros del usuario para validar
				user=etUser.getText().toString();
		        pass=etPass.getText().toString();
		        String dataUser[]={user,pass};
		        
		        //Se envia informacion del usuario para validar sus credenciales
		        Intent openIntent=new Intent(MainActivity.this, ValidarLoginActivity.class);
				openIntent.putExtra("dataUser", dataUser);
				startActivityForResult(openIntent,1);
			}        	
        });        
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            //Las credenciales del usuario son validas
        	if(resultCode == RESULT_OK){
                Bundle bundExtra=data.getExtras();
                String dataNamesFiles[]=bundExtra.getStringArray("resultNamesFiles");
                String dataSizeFiles[]=bundExtra.getStringArray("resultSizeFiles");
                String userLoged=bundExtra.getString("userLoged");
                
                //Se envia datos a la actividad de gestion de descarga.
                Intent openSpinnerIntent=new Intent(MainActivity.this, GestionarDescargaActivity.class);
                openSpinnerIntent.putExtra("NamesFiles", dataNamesFiles);
                openSpinnerIntent.putExtra("SizeFiles", dataSizeFiles);
                openSpinnerIntent.putExtra("UserLoged", userLoged);
                startActivity(openSpinnerIntent);
            	
            }
        	//Las credenciales del usuario no son validas
            if (resultCode == RESULT_CANCELED) {
            	String errorLogin=getResources().getString(R.string.errorLoginMsg).toString();
            	Toast.makeText(MainActivity.this,errorLogin, Toast.LENGTH_LONG).show(); 
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

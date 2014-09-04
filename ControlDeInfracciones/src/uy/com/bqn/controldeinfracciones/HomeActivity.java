package uy.com.bqn.controldeinfracciones;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeActivity extends Activity {
	String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
      //se define la estructura de datos que identifica al Usuario.
        final EditText etUser = (EditText)findViewById(R.id.editTextUser);
        final EditText etPass = (EditText)findViewById(R.id.editTextPass);
        
   
        //se define el Boton que lleva a la actividad que valida el login.
        Button btnLogin=(Button)findViewById(R.id.buttonHome);
        
        btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//se setean los parametros del usuario para validar
				user=etUser.getText().toString();
		        pass=etPass.getText().toString();
		        String dataUser[]={user,pass};
		        
		        //Se envia informacion del usuario para validar sus credenciales
		        Intent openIntent=new Intent(HomeActivity.this, ValidarLoginActivity.class);
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
                String token=bundExtra.getString("tokenSession");
                String userLoged=bundExtra.getString("userLoged");
                
                //Se envia datos a la actividad de Consulta de Infracciones.
                Intent openIntent=new Intent(HomeActivity.this, ConsultarInfraccionesActivity.class);
                openIntent.putExtra("tokenSession", token);
                openIntent.putExtra("UserLoged", userLoged);
                startActivity(openIntent);
            	
            }
        	//Las credenciales del usuario no son validas
            if (resultCode == RESULT_CANCELED) {
            	String errorLogin=getResources().getString(R.string.errorLoginMsg).toString();
            	Toast.makeText(HomeActivity.this,errorLogin, Toast.LENGTH_LONG).show(); 
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

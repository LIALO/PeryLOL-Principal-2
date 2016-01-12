package com.example.nabu.perylol.perylol_movil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;


public class MainActivity extends ActionBarActivity {

    EditText invocador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView portada = (ImageView)findViewById(R.id.imageView);
        UrlImageViewHelper.setUrlDrawable(portada, "http://cdn.leagueoflegends.com/game-info/1.1.9/images/content/modes-sr.jpg");
// Shared Preferences
        SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(this);
        invocador = (EditText) findViewById(R.id.nombre_invocador);
        invocador.setText(pref.getString("Summoner",""));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void enviar(View view){
        // Shared Preferences
        SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(this);

        // Editor for Shared preferences
        SharedPreferences.Editor editor = pref.edit();


        invocador = (EditText) findViewById(R.id.nombre_invocador);
        Intent eventos = new Intent(this, perfilInvocador.class);
        eventos.putExtra("nombre", invocador.getText() + "");
        try {
            // Storing name in pref
            editor.putString("Summoner", invocador.getText().toString());
            editor.commit();
            startActivity(eventos);
        }catch (Exception e){
            //pop_up(e.getMessage());
            Log.i("mensaje", e.getMessage());
        }
        //pop_up("Evento listo "+invocador.getText());
    }

    public void pop_up(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

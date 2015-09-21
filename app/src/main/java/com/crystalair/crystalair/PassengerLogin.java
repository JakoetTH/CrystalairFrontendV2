package com.crystalair.crystalair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class PassengerLogin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_passenger_login, menu);
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

    public void Login(View view)
    {
        //Intent intent = new Intent(this, PassengerDetails.class);
        //startActivity(intent);
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Passenger> {

        TextView viewUsername = (TextView) findViewById(R.id.MainUsername);
        CharSequence username = viewUsername.getText();
        TextView viewPassword = (TextView) findViewById(R.id.MainPassword);
        CharSequence password = viewPassword.getText();
        @Override
        protected Passenger doInBackground(Void... params) {
            try {
                String url = "http://crystalairdiy-redc.rhcloud.com/api/passenger?username=" + username + "&password=" + password;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Passenger passenger = restTemplate.getForObject(url, Passenger.class);
                return passenger;
            } catch (Exception e) {
                Log.e("PassengerLogin", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Passenger passenger) {
            TextView textView = (TextView) findViewById(R.id.MainUsername);
            textView.setText(passenger.getFirstName());
        }

    }

}

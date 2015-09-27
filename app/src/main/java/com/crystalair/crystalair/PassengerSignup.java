package com.crystalair.crystalair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class PassengerSignup extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_passenger_signup, menu);
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

    public void Signup(View view)
    {
        new HttpRequestTask().execute();
        finish();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, String> {

        TextView viewUsername = (TextView) findViewById(R.id.PSUUsername);
        CharSequence username = viewUsername.getText();
        TextView viewPassword = (TextView) findViewById(R.id.PSUPassword);
        CharSequence password = viewPassword.getText();
        TextView viewFirstname = (TextView) findViewById(R.id.PSUFirstname);
        CharSequence firstname = viewFirstname.getText();
        TextView viewLastname = (TextView) findViewById(R.id.PSULastname);
        CharSequence lastname = viewLastname.getText();
        TextView viewAddress = (TextView) findViewById(R.id.PSUAddress);
        CharSequence address = viewAddress.getText();
        TextView viewContact = (TextView) findViewById(R.id.PSUContact);
        CharSequence contact = viewContact.getText();
        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://crystalairdiy-redc.rhcloud.com/api/passenger/create?username="
                        + username + "&password=" + password + "&firstname=" + firstname + "&lastname="
                        + lastname + "&address=" + address + "&contact=" + contact;
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> entity = new HttpEntity<Object>(headers);
                ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity, Void.class);
                HttpStatus status = responseEntity.getStatusCode();
                    if(status == HttpStatus.CONFLICT)
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle("Invalid Username");
                        alertDialog.setMessage("The username is already in use");
                        alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }
                    else
                    {

                    }
                return null;
            } catch (Exception e) {
                Log.e("PassengerSignup", e.getMessage(), e);
            }
            return null;
        }


    }
}

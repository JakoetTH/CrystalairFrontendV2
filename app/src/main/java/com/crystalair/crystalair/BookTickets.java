package com.crystalair.crystalair;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class BookTickets extends AppCompatActivity {

    private static final String Username = "Username";
    private static final String Password = "Password";
    private static final Long flightid1 = Long.parseLong("1");
    private static final Long flightid2 = Long.parseLong("2");
    private static final Long flightid3 = Long.parseLong("3");
    private static final float price1 = Float.parseFloat("200.0");
    private static final float price2 = Float.parseFloat("300.0");
    private static final float price3 = Float.parseFloat("700.0");
    private static final String class1 = "thirdclass";
    private static final String class2 = "normalclass";
    private static final String class3 = "businessclass";


    private String usernameValue;
    private String passwordValue;
    private Passenger passenger;
    private Long passengerid;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tickets);
        Intent iDetails = getIntent();
        usernameValue = iDetails.getStringExtra(Username);
        passwordValue = iDetails.getStringExtra(Password);
        new HttpRequestTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_tickets, menu);
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

    public void BuyTicket(View view)
    {
        new HttpAddRequestTask().execute();
        Intent intent = new Intent(this, PassengerDetails.class);
        intent.putExtra(Username, usernameValue);
        intent.putExtra(Password, passwordValue);
        startActivity(intent);
        finish();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Passenger> {
        List<Flight> flist = new ArrayList<Flight>();
        @Override
        protected Passenger doInBackground(Void... params) {
            try {
                String url = "http://crystalairdiy-redc.rhcloud.com/api/passenger?username=" + usernameValue + "&password=" + passwordValue;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                passenger = restTemplate.getForObject(url, Passenger.class);
                return passenger;
            } catch (Exception e) {
                Log.e("BookTickets", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Passenger pass)
        {
            passengerid = passenger.getID();
        }

    }

    private class HttpAddRequestTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params){
            try
            {
                String url1 = "http://crystalairdiy-redc.rhcloud.com/api/ticket/create?userid=" + passengerid + "&flightid=" + flightid1 + "&price=" + price1 +"&ticketclass=" + class1;
                RestTemplate restTemplate1 = new RestTemplate();
                restTemplate1.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate1.getForObject(url1, Void.class);
                String url2 = "http://crystalairdiy-redc.rhcloud.com/api/ticket/create?userid=" + passengerid + "&flightid=" + flightid2 + "&price=" + price2 +"&ticketclass=" + class2;
                RestTemplate restTemplate2 = new RestTemplate();
                restTemplate2.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate2.getForObject(url2, Void.class);
                String url3 = "http://crystalairdiy-redc.rhcloud.com/api/ticket/create?userid=" + passengerid + "&flightid=" + flightid2 + "&price=" + price3 +"&ticketclass=" + class3;
                RestTemplate restTemplate3 = new RestTemplate();
                restTemplate3.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate3.getForObject(url3, Void.class);
            }
            catch (Exception e)
            {
                Log.e("BookTickets", e.getMessage(), e);
            }
            return null;
        }
    }

}

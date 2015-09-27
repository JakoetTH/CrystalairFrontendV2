package com.crystalair.crystalair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PassengerDetails extends AppCompatActivity{

    private static final String Username = "Username";
    private static final String Password = "Password";
    private static final String varnameId = "id";
    private static final String varnamePrice = "price";
    private static final String varnameClass = "ticketClass";

    private String usernameValue;
    private String passwordValue;
    private Passenger pass;
    private List<HashMap<String,String>> hashlist;
    private List<Ticket> ticketlist;
    private ListView ticketsView;
    private Long refundID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);
        Intent iLogin = getIntent();
        usernameValue = iLogin.getStringExtra(Username);
        passwordValue = iLogin.getStringExtra(Password);

        hashlist = new ArrayList<HashMap<String,String>>();
        ticketsView = (ListView) findViewById(R.id.PDticketlist);
        new HttpRequestTask().execute();
        ticketsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                refundID = Long.parseLong(((TextView) findViewById(R.id.lblTicketID)).getText().toString());
                //System.out.println(refundID);
                //System.out.println(id);
                //System.out.println(position);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_passenger_details, menu);
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

    public void BookTicket(View view)
    {
        Intent intent = new Intent(this, BookTickets.class);
        intent.putExtra(Username, usernameValue);
        intent.putExtra(Password, passwordValue);
        startActivity(intent);
    }

    public void RefundTicket(View view)
    {
        if(refundID==null)
        {
            //alert user to select a ticket object first
        }
        else
        {
            new HttpDeleteTask().execute();
            Intent intent = new Intent(this, PassengerDetails.class);
            intent.putExtra(Username, usernameValue);
            intent.putExtra(Password, passwordValue);
            startActivity(intent);
            finish();
        }
    }
    private class HttpDeleteTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String url = "http://crystalairdiy-redc.rhcloud.com/api/ticket/delete/" + refundID;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getForObject(url, Ticket.class);
            } catch (Exception e) {
                Log.e("PassengerDetails", e.getMessage(), e);
                /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Invalid Login");
                alertDialog.setMessage("Please make sure that your username and password are correct");
                alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();*/
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void levoid) {

        }
    }
    private class HttpRequestTask extends AsyncTask<Void, Void, Passenger> {
        @Override
        protected Passenger doInBackground(Void... params) {
            try {
                String url = "http://crystalairdiy-redc.rhcloud.com/api/passenger?username=" + usernameValue + "&password=" + passwordValue;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(url, Passenger.class);
            } catch (Exception e) {
                Log.e("PassengerDetails", e.getMessage(), e);
                /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Invalid Login");
                alertDialog.setMessage("Please make sure that your username and password are correct");
                alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();*/
            }
            return null;
        }

        @Override
        protected void onPostExecute(Passenger passenger) {
            pass = passenger;
            TextView usernameView = (TextView) findViewById(R.id.PDusername);
            usernameView.setText(pass.getUserName() + "");
            TextView firstnameView = (TextView) findViewById(R.id.PDfirstname);
            firstnameView.setText(pass.getFirstName() + "");
            ticketlist = pass.getTickets();
            HashMap<String, String> temphm;
            for (Ticket tick : ticketlist) {
                temphm = new HashMap<String, String>();
                temphm.put(varnamePrice, String.valueOf(tick.getPrice()));
                temphm.put(varnameClass, tick.getTicketClass());
                temphm.put(varnameId, String.valueOf(tick.getID()));
                hashlist.add(temphm);
            }
            if (hashlist.size() == 0) {
             } else {
                ListAdapter adapter = new SimpleAdapter(PassengerDetails.this, hashlist, R.layout.ticket_item,
                                                        new String[]{varnamePrice, varnameClass, varnameId}, new int[]{
                                                        R.id.lblTicketPrice, R.id.lblTicketClass, R.id.lblTicketID});
                ticketsView.setAdapter(adapter);
            }
        }
    }
}

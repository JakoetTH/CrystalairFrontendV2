package com.crystalair.crystalair;

import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/04/26.
 */
public interface FlightDisplay {
    public Long getID();
    public Long getDepartureTime();
    public Long getArrivalTime();
    public String getDepartureLocation();
    public String getArrivalLocation();
    public int getSeatsAvailable();
    public String displayFlightTimes();
    public String displayFlightLocations();
    public List<Ticket> getTickets();

}

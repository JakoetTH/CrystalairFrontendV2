package com.crystalair.crystalair;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/04/24.
 */
public class Flight implements FlightDisplay, Serializable{

    private Long id;
    private Date departureTime;
    private Date arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private int seatsAvailable;

    private List<Ticket> tickets;

    private Flight()
    {

    }

    public Flight(Builder builder)
    {
        this.id=builder.id;
        this.departureTime=builder.departureTime;
        this.arrivalTime=builder.arrivalTime;
        this.departureLocation=builder.departureLocation;
        this.arrivalLocation=builder.arrivalLocation;
        this.seatsAvailable=builder.seatsAvailable;
        this.tickets=builder.tickets;
    }
    @Override
    public Long getID()
    {
        return this.id;
    }
    @Override
    public Date getDepartureTime()
    {
        return this.departureTime;
    }
    @Override
    public Date getArrivalTime()
    {
        return this.arrivalTime;
    }
    @Override
    public String getDepartureLocation()
    {
        return this.departureLocation;
    }
    @Override
    public String getArrivalLocation()
    {
        return this.arrivalLocation;
    }
    @Override
    public int getSeatsAvailable()
    {
        return this.seatsAvailable;
    }
    @Override
    public String displayFlightTimes()
    {
        return "";
    }
    @Override
    public String displayFlightLocations()
    {
        return "";
    }
    @Override
    public List<Ticket> getTickets()
    {
        return this.tickets;
    }

    public static class Builder
    {
        private Long id;
        private Date departureTime;
        private Date arrivalTime;
        private String departureLocation;
        private String arrivalLocation;
        private int seatsAvailable;
        private List<Ticket> tickets;

        public Builder(String departureLocation)
        {
            this.departureLocation=departureLocation;
        }

        public Builder departureTime(Date departureTime)
        {
            this.departureTime=departureTime;
            return this;
        }

        public Builder arrivalTime(Date arrivalTime)
        {
            this.arrivalTime=arrivalTime;
            return this;
        }

        public Builder ID(Long ID)
        {
            this.id=ID;
            return this;
        }

        public Builder arrivalLocation(String arrivalLocation)
        {
            this.arrivalLocation=arrivalLocation;
            return this;
        }

        public Builder seatsAvailable(int seatsAvailable)
        {
            this.seatsAvailable=seatsAvailable;
            return this;
        }

        public Builder tickets(List<Ticket> tickets)
        {
            this.tickets=tickets;
            return this;
        }

        public Builder copy(Flight flight)
        {
            this.id=flight.getID();
            this.departureTime=flight.getDepartureTime();
            this.arrivalTime=flight.getArrivalTime();
            this.departureLocation=flight.getDepartureLocation();
            this.arrivalLocation=flight.getArrivalLocation();
            this.seatsAvailable=flight.getSeatsAvailable();
            this.tickets=flight.getTickets();
            return this;
        }

        public Flight build()
        {
            return new Flight(this);
        }
    }

}

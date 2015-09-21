package com.crystalair.crystalair;

import java.util.List;


public class Passenger implements PersonDetails{

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private List<Ticket> tickets;

    private Passenger()
    {

    }

    public Passenger(Builder builder)
    {
        this.id=builder.id;
        this.userName=builder.userName;
        this.password=builder.password;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.address=builder.address;
        this.contact=builder.contact;
        this.tickets=builder.tickets;
    }
    @Override
    public Long getID()
    {
        return this.id;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getPassword()
    {
        return this.password;
    }
    @Override
    public String getFirstName()
    {
        return this.firstName;
    }
    @Override
    public String getLastName()
    {
        return this.lastName;
    }
    @Override
    public String getAddress()
    {
        return this.address;
    }
    @Override
    public String getContact()
    {
        return this.contact;
    }
    public List<Ticket> getTickets(){
        return this.tickets;
    }

    public static class Builder
    {
        private Long id;
        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private String address;
        private String contact;
        private List<Ticket> tickets;

        public Builder(String userName)
        {
            this.userName=userName;
        }

        public Builder ID(Long ID)
        {
            this.id=ID;
            return this;
        }

        public Builder password(String password)
        {
            this.password=password;
            return this;
        }

        public Builder firstName(String firstName)
        {
            this.firstName=firstName;
            return this;
        }

        public Builder lastName(String lastName)
        {
            this.lastName=lastName;
            return this;
        }

        public Builder address(String address)
        {
            this.address=address;
            return this;
        }

        public Builder contact(String contact)
        {
            this.contact=contact;
            return this;
        }

        public Builder tickets(List<Ticket> tickets)
        {
            this.tickets=tickets;
            return this;
        }

        public Builder copy(Passenger passenger)
        {
            this.id=passenger.getID();
            this.userName=passenger.getUserName();
            this.password=passenger.getPassword();
            this.firstName=passenger.getFirstName();
            this.lastName=passenger.getLastName();
            this.address=passenger.getAddress();
            this.contact=passenger.getContact();
            this.tickets=passenger.getTickets();
            return this;
        }

        public Passenger build()
        {
            return new Passenger(this);
        }
    }

}

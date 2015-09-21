package com.crystalair.crystalair;
import java.io.Serializable;
/**
 * Created by student on 2015/04/24.
 */
public class Ticket implements TicketDetails{
    private Long id;
    private float price;
    private String ticketClass;

    private Ticket()
    {

    }
    public Ticket(Builder builder)
    {
        this.id=builder.id;
        this.price=builder.price;
        this.ticketClass=builder.ticketClass;
    }
    @Override
    public Long getID()
    {
        return this.id;
    }
    @Override
    public float getPrice()
    {
        return this.price;
    }
    @Override
    public String getTicketClass()
    {
        return this.ticketClass;
    }

    public static class Builder
    {
        private Long id;
        private float price;
        private String ticketClass;

        public Builder(String ticketClass) {
            this.ticketClass = ticketClass;
        }


        public Builder price(float price)
        {
            this.price=price;
            return this;
        }

        public Builder ID(Long ID)
        {
            this.id=ID;
            return this;
        }


        public Builder copy(Ticket ticket)
        {
            this.id=ticket.getID();
            this.price=ticket.getPrice();
            this.ticketClass=ticket.getTicketClass();
            return this;
        }

        public Ticket build()
        {
            return new Ticket(this);
        }
    }

}

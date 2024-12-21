package com.example.ticketing.tasks;

import com.example.ticketing.model.TicketPool;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerId;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int customerId, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(retrievalRate * 1000);
                ticketPool.retrieveTicket(customerId);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

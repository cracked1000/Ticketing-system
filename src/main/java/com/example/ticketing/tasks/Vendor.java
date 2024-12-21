package com.example.ticketing.tasks;

import com.example.ticketing.model.TicketPool;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int vendorId;
    private final int releaseRate;
    private int remainingTickets;

    public Vendor(TicketPool ticketPool, int vendorId, int releaseRate, int totalTicketsToRelease) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.releaseRate = releaseRate;
        this.remainingTickets = totalTicketsToRelease;
    }

    @Override
    public void run() {
        try {
            while (remainingTickets > 0) {
                Thread.sleep(releaseRate * 1000);
                if (remainingTickets > 0) {
                    ticketPool.addTicket("Ticket-" + vendorId, vendorId);
                    remainingTickets--;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
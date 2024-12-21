package com.example.ticketing.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<String> pool = new LinkedList<>();
    private final int capacity;

    public TicketPool(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addTicket(String ticket, int vendorId) throws InterruptedException {
        while (pool.size() >= capacity) {
            wait();
        }
        pool.add(ticket);
        System.out.println("Ticket added by Vendor ID-" + vendorId + ". Current size: " + pool.size());
        notifyAll();
    }

    public synchronized String retrieveTicket(int customerId) throws InterruptedException {
        while (pool.isEmpty()) {
            wait();
        }
        String ticket = pool.poll();
        System.out.println("Ticket bought by Customer ID-" + customerId + ". Current size: " + pool.size()+ " - Ticket is - "+ticket);
        notifyAll();
        return ticket;
    }
}

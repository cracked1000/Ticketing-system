package com.example.ticketing.service;

import com.example.ticketing.controller.TicketingController;
import com.example.ticketing.model.TicketPool;
import com.example.ticketing.tasks.Customer;
import com.example.ticketing.tasks.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketingService {
    private boolean running = false;
    private TicketPool ticketPool;
    private final List<Thread> threads;

    public TicketingService() {
        // Remove the hardcoded initialization
        this.threads = new ArrayList<>();
    }

    public synchronized String startSystem(int releaseRate, int retrievalRate, int totalTicket, int ticketPoolSize) {
        if (running) {
            return "System is already running.";
        }
        running = true;

        // Create the ticket pool with the user-specified size
        this.ticketPool = new TicketPool(ticketPoolSize);

        // Modify vendor and customer creation to use totalTicket
        for (int i = 0; i < 5; i++) { // 5 Vendors
            Thread vendor = new Thread(new Vendor(ticketPool, i + 1, releaseRate, totalTicket / 5));
            threads.add(vendor);
            vendor.start();
        }

        for (int i = 0; i < 10; i++) { // 10 Customers
            Thread customer = new Thread(new Customer(ticketPool, i + 1, retrievalRate));
            threads.add(customer);
            customer.start();
        }

        return "Ticketing system started successfully.";
    }

    // ... rest of the code remains the same
}
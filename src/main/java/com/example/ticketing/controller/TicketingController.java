package com.example.ticketing.controller;

import com.example.ticketing.model.TicketPool;
import com.example.ticketing.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketing")
public class TicketingController {

    @Autowired
    private TicketingService ticketingService;

    @PostMapping("/start")
    public String startSystem(
            @RequestParam int releaseRate,
            @RequestParam int retrievalRate,
            @RequestParam int ticketPoolSize,
            @RequestParam int totalTicket
    ) {
        // Add basic input validation
        if (releaseRate <= 0 || retrievalRate <= 0 || ticketPoolSize <= 0 || totalTicket <= 0) {
            return "Invalid input parameters. All parameters must be positive integers.";
        }

        if (ticketPoolSize > totalTicket) {
            return "Ticket pool size cannot be larger than total number of tickets.";
        }

        return ticketingService.startSystem(releaseRate, retrievalRate, totalTicket, ticketPoolSize);
    }

    // ... rest of the code remains the same
}
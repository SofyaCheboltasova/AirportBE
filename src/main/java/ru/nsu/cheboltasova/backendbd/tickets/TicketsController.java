package ru.nsu.cheboltasova.backendbd.tickets;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketsRpository ticketsRpository;

    public TicketsController(TicketsRpository jobRepository) {
        this.ticketsRpository = jobRepository;
    }


    @GetMapping("/getTicketsID")
    public List<Integer> getTicketsID() {
        return ticketsRpository.getTicketsID();
    }

     
    @GetMapping("/getTickets")
    public List<TicketsType> getTickets() {
        return ticketsRpository.getTickets();
    }

     
    @PostMapping("/createTicket")
    public void createTicket(@RequestBody TicketsType ticket) {
        ticketsRpository.createTicket(ticket);
    }

     
    @PutMapping("/updateJob/{ticketID}")
    public void updateTicket(@RequestBody TicketsType ticket) {
        ticketsRpository.updateTicket(ticket);
    }

     
    @DeleteMapping("/deleteJob/{ticketID}")
    public void deleteTicket(@PathVariable int ticketID) {
        ticketsRpository.deleteTicket(ticketID);
    }


     
    @GetMapping("/getCities")
    public List<String> getCities() {
        return (ticketsRpository.getCities());
    }

     
    @GetMapping("/getReturnedTicketsCountByFlight")
    public int getReturnedTicketsCountByFlight(@RequestParam int flightID) {
        return (ticketsRpository.getReturnedTicketsCountByFlight(flightID));
    }

     
    @GetMapping("/getReturnedTicketsCountByDay")
    public int getReturnedTicketsCountByDay(@RequestParam String time) {
        return ticketsRpository.getReturnedTicketsCountByDay(time);
    }

     
    @GetMapping("/getReturnedTicketsCountByCost")
    public int getReturnedTicketsCountByCost(@RequestParam int cost) {
        return ticketsRpository.getReturnedTicketsCountByCost(cost);
    }

     
    @GetMapping("/getReturnedTicketsCountByRoute")
    public int getReturnedTicketsCountByRoute(@RequestParam String start, @RequestParam String end) {
        return ticketsRpository.getReturnedTicketsCountByRoute(start, end);
    }

     
    @GetMapping("/getReturnedTicketsCountByAge")
    public int getReturnedTicketsCountByAge(@RequestParam int age) {
        return ticketsRpository.getReturnedTicketsCountByAge(age);
    }

     
    @GetMapping("/getReturnedTicketsCountBySex")
    public int getReturnedTicketsCountBySex(@RequestParam String sex) {
        return ticketsRpository.getReturnedTicketsCountBySex(sex);
    }

     
    @GetMapping("/getSoldTicketsAvgCountByRoute")
    public int getSoldTicketsAvgCountByRoute(@RequestParam String start, @RequestParam String end) {
        return ticketsRpository.getSoldTicketsAvgCountByRoute(start, end);
    }

}




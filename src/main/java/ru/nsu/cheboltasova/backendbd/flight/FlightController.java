package ru.nsu.cheboltasova.backendbd.flight;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/getFlights")
    public List<Integer> getFlights() {
        return flightRepository.getFlights();
    }

    @GetMapping("/getFullFlights")
    public List<FlightType> getFullFlights() {
        return flightRepository.getFullFlights();
    }

    @GetMapping("/getFlightsByRoute")
    public List<FlightType> getFlightsByRoute(@RequestParam String start, @RequestParam String end) {
        return flightRepository.getFlightsByRoute(start, end);
    }

    @GetMapping("/getFlightsByTicketCost")
    public List<FlightType> getFlightsByTicketCost() {
        return flightRepository.getFlightsByTicketCost();
    }

    @GetMapping("/getFlightsByDuration")
    public List<FlightDurationType> getFlightsByDuration() {
        return flightRepository.getFlightsByDuration();
    }

    @GetMapping("/getFlightsByAllParameters")
    public List<FlightDurationType> getFlightsByAllParameters(@RequestParam String start, @RequestParam String end) {
        return flightRepository.getFlightsByAllParameters(start, end);
    }

    @GetMapping("/getCancelledFlights")
    public List<FlightType> getCancelledFlights() {
        return flightRepository.getCancelledFlights();
    }


    @GetMapping("/getCancelledFlightsByStartPoint")
    public List<FlightType> getCancelledFlightsByStartPoint(@RequestParam String start) {
        return flightRepository.getCancelledFlightsByStartPoint(start);
    }

    @GetMapping("/getCancelledFlightsByRoute")
    public List<FlightType> getCancelledFlightsByRoute(@RequestParam String start, @RequestParam String end) {
        return flightRepository.getCancelledFlightsByRoute(start, end);
    }

    @GetMapping("/getFlightsByFreeSeatsCount")
    public List<FlightFreeSeatsType> getFlightsByFreeSeatsCount() {
        return flightRepository.getFlightsByFreeSeatsCount();
    }

    @GetMapping("/getFlightsByPercentageSeatsCount")
    public List<FlightPercentageType> getFlightsByPercentageSeatsCount() {
        return flightRepository.getFlightsByPercentageSeatsCount();
    }



    @GetMapping("/getDelayedFlights")
    public List<FlightType> getDelayedFlights() {
        return flightRepository.getDelayedFlights();
    }


    @GetMapping("/getDelayedFlightsByReason")
    public List<FlightType> getDelayedFlightsByReason(@RequestParam int id) {
        return flightRepository.getDelayedFlightsByReason(id);
    }

    @GetMapping("/getDelayedFlightsByRoute")
    public List<FlightType> getDelayedFlightsByRoute(@RequestParam String start, @RequestParam String end) {
        return flightRepository.getDelayedFlightsByRoute(start, end);
    }

    @GetMapping("/getDelayedFlightsByReturnedTickets")
    public int getDelayedFlightsByReturnedTickets() {
        return flightRepository.getDelayedFlightsByReturnedTickets();
    }

    @GetMapping("/getDelayReasons")
    public List<String> getDelayReasons() {
        return flightRepository.getDelayReasons();
    }


    @GetMapping("/getAircraftType")
    public List<String> getAircraftType() {
        return flightRepository.getAircraftType();
    }

    @GetMapping("/getFlightType")
    public List<String> getFlightType() {
        return flightRepository.getFlightType();
    }

    @GetMapping("/getFlightsByAircraftType")
    public List<FlightType> getFlightsByAircraftType(@RequestParam String type) {
        return flightRepository.getFlightsByAircraftType(type);
    }

    @GetMapping("/getAvgSoldTicketsByRoute")
    public int getAvgSoldTicketsByRoute(@RequestParam String start, @RequestParam String end) {
        return flightRepository.getAvgSoldTicketsByRoute(start, end);
    }

    @GetMapping("/getFlightsByDepartureTime")
    public List<FlightTimeType> getFlightsByDepartureTime() {
        return flightRepository.getFlightsByDepartureTime();
    }

    @GetMapping("/getFlightsByFlightType")
    public List<FlightType> getFlightsByFlightType(@RequestParam String id) {
        return flightRepository.getFlightsByFlightType(id);
    }

    @GetMapping("/getFlightsByStartPoint")
    public List<FlightType> getFlightsByStartPoint(@RequestParam String start) {
        return flightRepository.getFlightsByStartPoint(start);
    }
}
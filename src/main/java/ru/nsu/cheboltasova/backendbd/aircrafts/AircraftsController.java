package ru.nsu.cheboltasova.backendbd.aircrafts;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/aircrafts")
public class AircraftsController {
    private final AircraftsRepository aircraftsRepository;

    public AircraftsController(AircraftsRepository jobRepository) {
        this.aircraftsRepository = jobRepository;
    }

    
    @GetMapping("/getAircraftsID")
    public List<Integer> getAircraftsID() {
        return aircraftsRepository.getAircraftsID();
    }

    
    @GetMapping("/getAirportsName")
    public List<String> getAirportsName() {
        return aircraftsRepository.getAirportsName();
    }


    
    @GetMapping("/getAircraftsByAirport")
    public List<AircraftsType> getAircraftsByAirport(@RequestParam String airport) {
        return aircraftsRepository.getAircraftsByAirport(airport);
    }

    
    @GetMapping("/getAircraftsByFlightsCount")
    public List<AircraftAndCountType> getAircraftsByFlightsCount(@RequestParam int count) {
        return aircraftsRepository.getAircraftsByFlightsCount(count);
    }

    
    @GetMapping("/getAircraftsByLandInAirport")
    public List<AircraftAndArrivalTimeType> getAircraftsByLandInAirport(@RequestParam String airport) {
        return aircraftsRepository.getAircraftsByLandInAirport(airport);
    }

    
    @GetMapping("/getAircraftsInAirportByTime")
    public List<AircraftAndDepartureTimeType> getAircraftsInAirportByTime(@RequestParam String time,
                                                                          @RequestParam String airport) {
        return aircraftsRepository.getAircraftsInAirportByTime(time, airport);
    }

    
    @GetMapping("/getAircraftsInAirportByTechInspect")
    public List<AircraftsType> getAircraftsInAirportByTechInspect(@RequestParam String startTime,
                                                                  @RequestParam String endTime) {
        return aircraftsRepository.getAircraftsInAirportByTechInspect(startTime, endTime);
    }
    
    @GetMapping("/getAircraftsByRepairTime")
    public List<AircraftsType> getAircraftsByRepairTime(@RequestParam String startTime,
                                                        @RequestParam String endTime) {
        return aircraftsRepository.getAircraftsByRepairTime(startTime, endTime);
    }
    
    @GetMapping("/getAircraftsByRepairCount")
    public List<AircraftAndCountServiceType> getAircraftsByRepairCount(@RequestParam int count) {
        return aircraftsRepository.getAircraftsByRepairCount(count);
    }
    
    @GetMapping("/getAircraftsByFlightCountBeforeRepair")
    public List<AircraftAndCountType> getAircraftsByFlightCountBeforeRepair() {
        return aircraftsRepository.getAircraftsByFlightCountBeforeRepair();
    }
    
    @GetMapping("/getAircraftsByAge")
    public List<AircraftsType> getAircraftsByAge(@RequestParam int age) {
        return aircraftsRepository.getAircraftsByAge(age);
    }

    
    @GetMapping("/getAircrafts")
    public List<AircraftsType> getAircrafts() {
        return aircraftsRepository.getAircrafts();
    }

    
    @PostMapping("/createAircraft")
    public void createAircraft(@RequestBody AircraftsType aircraft) {
        aircraftsRepository.createAircarft(aircraft);
    }

    
    @DeleteMapping("/deleteAircraft/{id}")
    public void deleteAircraft(@PathVariable int id) {
        aircraftsRepository.deleteAircraft(id);
    }
    
    @PutMapping("/updateAircraft/{aircraftID}")
    public void updateAircraft(@RequestBody AircraftsType arg) {
        aircraftsRepository.updateAircraft(arg);
    }

}
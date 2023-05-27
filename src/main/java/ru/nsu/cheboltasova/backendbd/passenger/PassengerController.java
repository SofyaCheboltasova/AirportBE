package ru.nsu.cheboltasova.backendbd.passenger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerRpository PassengersRpository;

    public PassengerController(PassengerRpository jobRepository) {
        this.PassengersRpository = jobRepository;
    }

    @GetMapping("/getPassengersID")
    public List<Integer> getPassengersID() {
        return PassengersRpository.getPassengersID();
    }

    @GetMapping("/getPassengers")
    public List<PassengerType> getPassengers() {
        return PassengersRpository.getPassengers();
    }

    @GetMapping("/getBaggage")
    public List<BaggageType> getBaggage() {
        return PassengersRpository.getBaggage();
    }

    @PostMapping("/createPassenger")
    public void createPassenger(@RequestBody PassengerType Passenger) {
        PassengersRpository.createPassenger(Passenger);
    }

    @PutMapping("/updatePassenger/{PassengerID}")
    public void updatePassenger(@RequestBody PassengerType Passenger) {
        PassengersRpository.updatePassenger(Passenger);
    }

    @DeleteMapping("/deletePassenger/{PassengerID}")
    public void deletePassenger(@PathVariable int PassengerID) {
        PassengersRpository.deletePassenger(PassengerID);
    }


    @GetMapping("/getPassengersByFlight")
    public List<PassengerType> getPassengersByFlight(@RequestParam int flightID) {
        return (PassengersRpository.getPassengersByFlight(flightID));
    }

    @GetMapping("/getPassengersByAbroad")
    public List<PassengerDateType> getPassengersByAbroad(@RequestParam String time) {
        return PassengersRpository.getPassengersByAbroad(time);
    }

    @GetMapping("/getPassengersByBaggage")
    public List<PassengerBaggageType> getPassengersByBaggage() {
        return PassengersRpository.getPassengersByBaggage();
    }

    @GetMapping("/getPassengersByDate")
    public List<PassengerDateType> getPassengersByDate(@RequestParam String time) {
        return PassengersRpository.getPassengersByDate(time);
    }

    @GetMapping("/getPassengersByAge")
    public List<PassengerType> getPassengersByAge(@RequestParam int age) {
        return PassengersRpository.getPassengersByAge(age);
    }

    @GetMapping("/getPassengersBySex")
    public List<PassengerType> getPassengersBySex(@RequestParam String sex) {
        return PassengersRpository.getPassengersBySex(sex);
    }

}




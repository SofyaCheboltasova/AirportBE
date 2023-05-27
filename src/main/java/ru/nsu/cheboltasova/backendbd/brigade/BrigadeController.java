package ru.nsu.cheboltasova.backendbd.brigade;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.nsu.cheboltasova.backendbd.employees.EmployeeType;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/brigade")
public class BrigadeController {
    private final BrigadeRepository brigadeRepository;

    public BrigadeController(BrigadeRepository brigadeRepository) {
        this.brigadeRepository = brigadeRepository;
    }

    
    @GetMapping("/getBrigades")
    public List<Integer> getBrigades() {
        return brigadeRepository.getBrigades();
    }

    
    @GetMapping("/getBrigadesByJob")
    public List<Integer> getBrigadesByJob(@RequestParam int jobID) {
        return brigadeRepository.getBrigadesByJob(jobID);
    }


    
    @GetMapping("/getFullBrigades")
    public List<BrigadeFullType> getDepartments() {
        return brigadeRepository.getFullBrigades();
    }


    @PostMapping("/createBrigade")
    public void createBrigade(@RequestBody int brigade) {
        brigadeRepository.createBrigade(brigade);
    }

    @PutMapping("/updateBrigade/{brigadeID}")
    public void updateBrigade(@RequestBody BrigadeType brigade) {
        brigadeRepository.updateBrigade(brigade);
    }

    @DeleteMapping("/deleteBrigade/{brigadeID}")
    public void deleteBrigade(@PathVariable int brigadeID) {
        brigadeRepository.deleteBrigade(brigadeID);
    }

    
    @GetMapping("/getEmployeesByBrigade")
    public List<EmployeeType> getEmployeesByBrigade(@RequestParam int brigadeID) {
        return brigadeRepository.getEmployeesByBrigade(brigadeID);
    }

    
    @GetMapping("/getEmployeesByAvgSalary")
    public List<BrigadeAvgSalaryType> getEmployeesByAvgSalary(@RequestParam int brigadeID) {
        return brigadeRepository.getEmployeesByAvgSalary(brigadeID);
    }
}
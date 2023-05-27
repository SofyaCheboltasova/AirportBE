package ru.nsu.cheboltasova.backendbd.employees;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

      
    @GetMapping("/getEmployeesID")
    public List<Integer> getEmployeesID() {
        return employeeRepository.getEmployeesID();
    }


    @PostMapping("/createEmployee")
    public void createEmployee(@RequestBody EmployeeType Employee) {
        employeeRepository.createEmployee(Employee);
    }

    @PutMapping("/updateEmployee/{employeeID}")
    public void updateEmployee(@RequestBody EmployeeType Employee) {
        employeeRepository.updateEmployee(Employee);
    }

    @DeleteMapping("/deleteEmployee/{employeeID}")
    public void deleteEmployee(@PathVariable int employeeID) {
        employeeRepository.deleteEmployee(employeeID);
    }


      
    @GetMapping("/getEmployees")
    public List<EmployeeType> getEmployees() {
        return employeeRepository.getEmployeesGroupedByDepartments();
    }

      
    @GetMapping("/getEmployeesByAge")
    public List<EmployeeType> getEmployeesByAge(@RequestParam int age) {
        return employeeRepository.getEmployeesByAge(age);
    }

      
    @GetMapping("/getEmployeesByFlight")
    public List<EmployeeType> getEmployeesByFlight(@RequestParam int flight) {
        return employeeRepository.getEmployeesByFlight(flight);
    }

      
    @GetMapping("/getPilotsByMed")
    public List<PilotMedCheckUpType> getPilotsByMed(@RequestParam int year) {
        return employeeRepository.getPilotsByMed(year);
    }

      
    @GetMapping("/getFilterPilots")
    public List<EmployeeType> getFilterPilots(@RequestParam String sex,
                                              @RequestParam int age,
                                              @RequestParam int salary) {
        return employeeRepository.getFilterPilots(sex, age, salary);
    }
}
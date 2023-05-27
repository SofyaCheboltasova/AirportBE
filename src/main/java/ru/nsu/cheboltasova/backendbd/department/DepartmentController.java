package ru.nsu.cheboltasova.backendbd.department;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.nsu.cheboltasova.backendbd.employees.EmployeeType;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    
    @GetMapping("/getDepartmentNames")
    public List<String> getDepartmentNames() {
        return departmentRepository.getDepartmentNames();
    }

    
    @GetMapping("/getDepartmentsID")
    public List<Integer> getDepartmentsID() {
        return departmentRepository.getDepartmentsID();
    }

    
    @GetMapping("/getEmployeesByDepartment")
    public List<EmployeeType> getEmployeesByDepartment(@RequestParam String jobposition) {
        return departmentRepository.getEmployeesByDepartment(jobposition);
    }

    
    @GetMapping("/getDepartments")
    public List<DepartmentFullType> getDepartments() {
        return departmentRepository.getDepartments();
    }

    @PostMapping("/createDepartment")
    public void createDepartment(@RequestBody DepartmentType arg) {
        departmentRepository.createDepartment(arg);
    }

    @PutMapping("/updateDepartment/{DepartmentID}")
    public void updateDepartment(@RequestBody DepartmentType arg) {
        departmentRepository.updateDepartment(arg);
    }

    @DeleteMapping("/deleteDepartment/{DepartmentID}")
    public void deleteDepartment(@PathVariable int DepartmentID) {
        departmentRepository.deleteDepartment(DepartmentID);
    }
}

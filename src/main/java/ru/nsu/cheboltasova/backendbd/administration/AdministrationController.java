package ru.nsu.cheboltasova.backendbd.administration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/administrations")
public class AdministrationController {
    private final AdministrationRepository administrationRepository;

    public AdministrationController(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
    }

    
    @GetMapping("/administration")
    public List<AdministrationAgeType> getAdministration() {
        return administrationRepository.getAdministration();
    }

    
    @GetMapping("/getChiefsID")
    public List<Integer> getChiefsID() {
        return administrationRepository.getChiefsID();
    }

    
    @GetMapping("/filteredAdministration")
    public List<AdministrationAgeType> getFilteredAdministration(@RequestParam Integer lengthOfService,
                                                              @RequestParam String sex,
                                                              @RequestParam String age,
                                                              @RequestParam Boolean hasChildren,
                                                              @RequestParam(required = false) Integer quantityOfChildren,
                                                              @RequestParam Integer salary) {
        return administrationRepository.getFilteredAdministration(lengthOfService, sex, age, hasChildren, quantityOfChildren, salary);
    }

    @PostMapping("/")
    public void createAdministration(@RequestBody AdministrationType administration) {
        administrationRepository.createAdministration(administration);
    }

    @PutMapping("/updateAdministration/{chiefID}")
    public void updateAdministration(@PathVariable int chiefID, @RequestBody AdministrationType administration) {
        administration.setChiefID(chiefID);
        administrationRepository.updateAdministration(administration);
    }

    @DeleteMapping("/{chiefID}")
    public void deleteAdministration(@PathVariable int chiefID) {
        administrationRepository.deleteAdministration(chiefID);
    }
}

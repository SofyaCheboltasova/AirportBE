package ru.nsu.cheboltasova.backendbd.job;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/jobs")
public class JobController {
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/getJobsID")
    public List<Integer> getJobsID() {
        return jobRepository.getJobsID();
    }

    @GetMapping("/getJobs")
    public List<JobType> getJobs() {
        return jobRepository.getJobs();
    }

    @GetMapping("/getJobsName")
    public List<String> getJobsName() {
        return jobRepository.getJobsName();
    }

    @PostMapping("/createJob")
    public void createJob(@RequestBody JobType job) {
        jobRepository.createJob(job);
    }

    @PutMapping("/updateJob/{jobID}")
    public void updateJob(@RequestBody JobType job) {
        jobRepository.updateJob(job);
    }

    @DeleteMapping("/deleteJob/{jobID}")
    public void deleteJob(@PathVariable int jobID) {
        jobRepository.deleteJob(jobID);
    }
}
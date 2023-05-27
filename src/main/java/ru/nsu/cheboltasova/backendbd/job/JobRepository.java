package ru.nsu.cheboltasova.backendbd.job;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobRepository {
    private final JdbcTemplate jdbcTemplate;

    public JobRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getJobsID() {
        String query = "SELECT jobid FROM jobs ORDER BY jobid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<JobType> getJobs() {
        String query = "SELECT * FROM jobs ORDER BY jobid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(JobType.class));
    }

    public List<String> getJobsName() {
        String query = "SELECT jobposition FROM jobs ORDER BY jobid";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public void createJob(JobType job) {
        String getLastIdQuery = "SELECT MAX(jobid) FROM jobs";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class) + 1;
        String query = "INSERT INTO jobs (jobid, jobposition) VALUES (?, ?)";
        jdbcTemplate.update(query, lastId, job.getJobPosition());
    }

    public void updateJob(JobType job) {
        String query = "UPDATE Jobs SET JobPosition = ? WHERE JobID = ?";
        jdbcTemplate.update(query, job.getJobPosition(), job.getJobID());
    }

    public void deleteJob(int jobID) {
        String query = "DELETE FROM jobs WHERE jobid = ?";
        jdbcTemplate.update(query, jobID);
    }
}

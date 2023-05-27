package ru.nsu.cheboltasova.backendbd.brigade;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.cheboltasova.backendbd.employees.EmployeeType;

import java.util.List;

@Repository
public class BrigadeRepository {
    private final JdbcTemplate jdbcTemplate;

    public BrigadeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getBrigades() {
        String query = "SELECT DISTINCT brigadeid FROM brigade ORDER BY brigadeid";

        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<Integer> getBrigadesByJob(int jobID) {
        String query = "" +
                "SELECT DISTINCT brigadeid " +
                "FROM brigade " +
                "WHERE (brigadetype = ?)" +
                "ORDER BY brigadeid";

        return jdbcTemplate.queryForList(query, Integer.class, jobID);
    }

    public List<BrigadeFullType> getFullBrigades() {
        String query = "" +
                "SELECT brigadeid, brigade.departmentid, brigadetype, jobposition\n" +
                "FROM brigade, jobs\n" +
                "WHERE jobs.jobid = brigadetype;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(BrigadeFullType.class));
    }

    public void createBrigade(int brigade) {
        String getLastIdQuery = "SELECT MAX(brigadeid) FROM brigade";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class) + 1;

        String query = "INSERT INTO brigade (brigadeid, departmentID, brigadeType) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, lastId, brigade, brigade);
    }

    public void updateBrigade(BrigadeType brigade) {
        String query = "UPDATE brigade SET departmentID = ?, brigadeType = ? WHERE brigadeID = ?";
        jdbcTemplate.update(query, brigade.getBrigadeType(), brigade.getBrigadeType(), brigade.getBrigadeID());
    }

    public void deleteBrigade(int brigadeID) {
        String query = "DELETE FROM brigade WHERE brigadeid = ?";
        jdbcTemplate.update(query, brigadeID);
    }

    public List<EmployeeType> getEmployeesByBrigade(int brigadeID) {
        String query = "" +
                "SELECT  *\n" +
                "FROM employees, brigade, jobs\n" +
                "WHERE (employees.brigadeid = brigade.brigadeid AND brigadetype = jobs.jobid AND brigade.brigadeid = ?)\n" +
                "GROUP BY (employeeid), jobs.jobid, brigade.brigadeid;\n";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class), brigadeID);
    }

    public List<BrigadeAvgSalaryType> getEmployeesByAvgSalary(int brigadeID) {
        String query = "WITH avgSalary AS (\n" +
                "        SELECT employees.brigadeid, AVG(salary) as salary\n" +
                "        FROM   employees, brigade\n" +
                "        WHERE  (employees.brigadeid = brigade.brigadeid)\n" +
                "        GROUP BY employees.brigadeid)\n" +
                "\n" +
                "SELECT *, avgSalary.salary\n" +
                "FROM employees, brigade, avgSalary\n" +
                "WHERE ( brigade.brigadeid = ?\n" +
                "        AND brigade.brigadeid = employees.brigadeid\n" +
                "        AND employees.salary = avgSalary.salary)\n" +
                "GROUP BY employeeid, brigade.brigadeid, avgSalary.salary, avgSalary.brigadeid\n" +
                "ORDER BY EmployeeFullName;";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(BrigadeAvgSalaryType.class), brigadeID);
    }
}
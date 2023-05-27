package ru.nsu.cheboltasova.backendbd.department;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.cheboltasova.backendbd.employees.EmployeeType;

import java.util.List;

@Repository
public class DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getDepartmentNames() {
        String query = "SELECT DISTINCT jobposition FROM jobs";

        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<Integer> getDepartmentsID() {
        String query = "SELECT DISTINCT departmentid FROM department ORDER BY departmentid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<EmployeeType> getEmployeesByDepartment(String jobposition) {
        String query = "SELECT employees.departmentid, *\n" +
                "FROM employees, jobs\n" +
                "WHERE jobposition = ? AND jobs.jobid = departmentid\n" +
                "GROUP BY employees.departmentid, employeeid, jobs.jobid\n" +
                "ORDER BY employees.departmentid;\n";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class), jobposition);
    }

    public List<DepartmentFullType> getDepartments() {
        String query = "" +
                "SELECT departmentid, jobs.jobid, jobposition, administration.chiefid, chieffullname\n" +
                "FROM department, administration, jobs\n" +
                "WHERE administration.chiefid = department.chiefid AND jobs.jobid = department.jobid;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(DepartmentFullType.class));
    }

    public void createDepartment(DepartmentType department) {
        String getLastIdQuery = "SELECT MAX(departmentid) FROM department";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class) + 1;
        String query = "INSERT INTO department (departmentID, chiefID, jobid) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, lastId, department.getChiefID(), department.getJobID());
    }

    public void updateDepartment(DepartmentType department) {
        String query = "UPDATE department SET chiefID = ?, jobid = ? WHERE departmentid = ?";
        jdbcTemplate.update(query, department.getChiefID(), department.getJobID(), department.getDepartmentID());
    }

    public void deleteDepartment(int departmentID) {
        String getLastIdQuery = "SELECT MAX(departmentid) FROM department";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class);

        if (departmentID > lastId) {
            throw new IllegalArgumentException("Invalid departmentID");
        }

        String query = "DELETE FROM department WHERE departmentid = ?";
        jdbcTemplate.update(query, departmentID);
    }
}


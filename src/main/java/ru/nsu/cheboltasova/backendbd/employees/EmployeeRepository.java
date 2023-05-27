package ru.nsu.cheboltasova.backendbd.employees;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getEmployeesID() {
        String query = "SELECT employeeid FROM employees ORDER BY employeeid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public void createEmployee(EmployeeType employee) {
        String getLastIdQuery = "SELECT MAX(employeeid) FROM employees";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class) + 1;

        String query = "INSERT INTO employees (employeeid, brigadeID, departmentid, jobID, employeeFullName, age, sex, salary, lengthofservice, haschildren, quantityofchildren) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, lastId, employee.getBrigadeID(), employee.getJobID(), employee.getJobID(), employee.getEmployeeFullName(), employee.getAge(), employee.getSex(), employee.getSalary(), employee.getLengthOfService(), employee.isHasChildren(), employee.getQuantityOfChildren());
    }

    public void updateEmployee(EmployeeType employee) {
        String query = "UPDATE employees SET brigadeID = ?, departmentid = ?, jobID = ?, employeeFullName = ?, age = ?, sex = ?, salary = ?, lengthofservice = ?, haschildren = ?, quantityofchildren = ? WHERE employeeID = ?";
        jdbcTemplate.update(query, employee.getBrigadeID(), employee.getJobID(), employee.getJobID(), employee.getEmployeeFullName(), employee.getAge(), employee.getSex(), employee.getSalary(), employee.getLengthOfService(), employee.isHasChildren(), employee.getQuantityOfChildren(), employee.getEmployeeID());
    }

    public void deleteEmployee(int employeeID) {
        String query = "DELETE FROM employees WHERE employeeID = ?";
        jdbcTemplate.update(query, employeeID);
    }

    public List<EmployeeType> getEmployeesGroupedByDepartments() {
        String query = "SELECT *\n" +
                "FROM employees, jobs\n" +
                "WHERE jobs.jobid = departmentid\n" +
                "GROUP BY employees.departmentid, employeeid, jobs.jobid\n" +
                "ORDER BY employees.departmentid";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class));
    }

    public List<EmployeeType> getEmployeesByAge(int age) {
        String query = "SELECT *\n" +
                "FROM employees, jobs\n" +
                "WHERE jobs.jobid = departmentid AND age >= ?\n" +
                "GROUP BY employees.departmentid, employeeid, jobs.jobid\n" +
                "ORDER BY employees.departmentid";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class), age);
    }

    public List<EmployeeType> getEmployeesByFlight(int flight) {
        String query = "SELECT employeeid, employeefullname, jobposition, employees.brigadeid, employees.departmentid, " +
                "employees.jobid, employees.age, sex, salary, lengthofservice, haschildren, quantityofchildren\n" +
                "FROM employees, flights, aircraft, brigade, jobs\n" +
                "WHERE ( flights.flightid = ? AND employees.jobid = jobs.jobid AND flights.aircraftid = aircraft.aircraftid AND brigade.brigadeid = employees.brigadeid\n" +
                "        AND (brigadepilotsid = brigade.brigadeid\n" +
                "        OR brigadeserviceid = brigade.brigadeid\n" +
                "        OR brigadetechid = brigade.brigadeid))";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class), flight);
    }

    public List<PilotMedCheckUpType> getPilotsByMed(int year) {
        String yearString = year + "%"; // Преобразование числа в строку
        String query = "" +
                "WITH pilots AS (\n" +
                "   SELECT employees.employeeid as id\n" +
                "   FROM   employees, employeeskills, profskills\n" +
                "   WHERE employeeskills.employeeid = employees.employeeid\n" +
                "       AND skillname = 'Дата медосмотра'\n" +
                "       AND skillval LIKE ?\n" +
                "    )\n" +
                "\n" +
                "SELECT employees.employeeid, employeefullname, skillval\n" +
                "FROM  employees, employeeskills, profskills, pilots\n" +
                "WHERE\n" +
                "      employees.employeeid = employeeskills.employeeid\n" +
                "      AND employeeskills.profskillid = profskills.profskillid\n" +
                "      AND pilots.id = employees.employeeid\n" +
                "      AND profskills.skillname = 'Результат медосмотра'\n" +
                "GROUP BY employees.employeeid, employeefullname, skillval";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PilotMedCheckUpType.class), yearString);
    }

    public List<EmployeeType> getFilterPilots(String sex, int age, int salary) {
        String query = "SELECT * " +
                "FROM employees, jobs " +
                "WHERE employees.jobid = 4 AND employees.jobid = jobs.jobid AND Sex = ? AND Age > ? AND Salary > ? " +
                "ORDER BY employeefullname, sex, age, salary, jobs.jobid";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EmployeeType.class), sex, age, salary);
    }
}
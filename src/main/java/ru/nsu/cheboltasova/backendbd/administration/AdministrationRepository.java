package ru.nsu.cheboltasova.backendbd.administration;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class AdministrationRepository {
    private final JdbcTemplate jdbcTemplate;

    public AdministrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getChiefsID() {
        String query = "SELECT chiefid FROM Administration ORDER BY chiefid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<AdministrationAgeType> getAdministration() {
        String query = "" +
                "SELECT ChiefID, ChiefFullName, CAST(DATE_PART('year', age(Age)) AS int) AS Age, Sex, Salary, " +
                "LengthOfService, HasChildren, QuantityOfChildren " +
                "FROM Administration " +
                "ORDER BY ChiefID";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AdministrationAgeType.class));
    }

    public List<AdministrationAgeType> getFilteredAdministration(int lengthOfService, String sex, String age, boolean hasChildren, Integer quantityOfChildren, int salary) {
        String query = "" +
                "SELECT ChiefID, ChiefFullName, CAST(DATE_PART('year', age(Age)) AS int) as Age, Sex, Salary, " +
                "LengthOfService, HasChildren, QuantityOfChildren " +
                "FROM Administration " +
                "WHERE LengthOfService > ? AND Sex = ? AND " +
                "CAST(DATE_PART('year', age(TO_TIMESTAMP(?,'YYYY-MM-DD')))as int) < CAST(DATE_PART('year', age(Age)) AS int) " +
                "AND HasChildren = ? AND QuantityOfChildren >= ? AND Salary > ? " +
                "ORDER BY ChiefFullName";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AdministrationAgeType.class), lengthOfService, sex, age, hasChildren, quantityOfChildren, salary);
    }


    public void createAdministration(AdministrationType administration) {
        String getLastIdQuery = "SELECT MAX(chiefid) FROM administration";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class);
        if (lastId == null) {
            lastId = 0;
        }
        String query = "" +
                "INSERT INTO administration (ChiefID, ChiefFullName, Age, Sex, Salary, LengthOfService, HasChildren, QuantityOfChildren) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, lastId + 1, administration.getChiefFullName(), administration.getAge(),
                administration.getSex(), administration.getSalary(), administration.getLengthOfService(),
                administration.isHasChildren(), administration.getQuantityOfChildren());
    }

    public void updateAdministration(AdministrationType administration) {
        String query = "UPDATE administration SET ChiefFullName = ?, Age = ?, Sex = ?, Salary = ?, LengthOfService = ?, " +
                "HasChildren = ?, QuantityOfChildren = ? WHERE ChiefID = ?";
        jdbcTemplate.update(query, administration.getChiefFullName(), administration.getAge(), administration.getSex(),
                administration.getSalary(), administration.getLengthOfService(), administration.isHasChildren(),
                administration.getQuantityOfChildren(), administration.getChiefID());
    }

    public void deleteAdministration(int chiefID) {
        String getLastIdQuery = "SELECT MAX(chiefid) FROM administration";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class);

        if (chiefID > lastId) {
            throw new IllegalArgumentException("Invalid departmentID");
        }
        String query = "DELETE FROM administration WHERE ChiefID = ?";
        jdbcTemplate.update(query, chiefID);
    }
}


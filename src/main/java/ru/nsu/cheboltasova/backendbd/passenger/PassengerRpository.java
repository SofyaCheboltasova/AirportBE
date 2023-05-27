package ru.nsu.cheboltasova.backendbd.passenger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PassengerRpository {
    private final JdbcTemplate jdbcTemplate;
    public PassengerRpository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getPassengersID() {
        String query = "SELECT Passengerid FROM passenger ORDER BY passengerid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<PassengerType> getPassengers() {
        String query = "SELECT * FROM passenger ORDER BY passengerid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerType.class));
    }

    public void createPassenger(PassengerType Passenger) {
        String getLastIdQuery = "SELECT MAX(Passengerid) FROM passenger";
        int lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class);
        int newId = lastId + 1;

        String createQuery = "INSERT INTO passenger (passengerid, PassengerFullName, Age, sex, passport, foreignpassport) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(createQuery, newId, Passenger.getPassengerFullName(), Passenger.getAge(), Passenger.getSex(), Passenger.getPassport(), Passenger.getForeignPassport());
    }

    public void updatePassenger(PassengerType Passenger) {
        String query = "UPDATE passenger SET PassengerID = ?, PassengerFullName = ?, age = ?, sex = ?, passport = ?, foreignpassport = ? WHERE PassengerID = ?";
        jdbcTemplate.update(query, Passenger.getPassengerID(), Passenger.getPassengerFullName(), Passenger.getAge(), Passenger.getSex(), Passenger.getPassport(), Passenger.getForeignPassport(), Passenger.getPassengerID());
    }

    public void deletePassenger(int PassengerID) {
        String query = "DELETE FROM passenger WHERE Passengerid = ?";
        jdbcTemplate.update(query, PassengerID);
    }

    public List<BaggageType> getBaggage() {
        String query = "" +
                "SELECT * " +
                "FROM baggage, passenger, tickets " +
                "WHERE (baggage.passengerid = passenger.passengerid AND passenger.passengerid = tickets.passengerid)" +
                "ORDER BY baggageid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(BaggageType.class));
    }


    public List<PassengerType> getPassengersByFlight(int flightID) {
        String query = "" +
                "SELECT passenger.passengerid, passengerfullname, age, sex, passport, foreignpassport, flightid\n" +
                "FROM tickets, passenger\n" +
                "WHERE ( tickets.passengerid =  passenger.passengerid AND flightid = ?);";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerType.class), flightID);
    }

    public List<PassengerDateType> getPassengersByAbroad(String time) {
        LocalDate startDate = LocalDate.parse(time);
        String query = "" +
                "SELECT DISTINCT passenger.passengerid, passengerfullname, age, sex, passport, foreignpassport, ticketid, schedule.flightid, scheduleddeparture\n" +
                "FROM tickets, flights, flighttype, schedule, passenger\n" +
                "WHERE ( tickets.flightid = flights.flightid AND\n" +
                "        flights.flighttype = flighttype.id AND\n" +
                "        flights.flightid = schedule.flightid AND\n" +
                "        tickets.passengerid = passenger.passengerid AND\n" +
                "        flightstatus IN (4, 5) AND\n" +
                "        DATE(scheduleddeparture) = ? AND\n" +
                "        (flighttype.flighttype = 'Международный' OR flighttype.flighttype = 'Чартерный')\n" +
                ");";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerDateType.class), startDate);
    }

    public List<PassengerDateType> getPassengersByDate(String time) {
        LocalDate startDate = LocalDate.parse(time);
        String query = "" +
                "SELECT DISTINCT *\n" +
                "FROM tickets, flights, flighttype, schedule, passenger\n" +
                "WHERE ( tickets.flightid = flights.flightid AND\n" +
                "        flights.flighttype = flighttype.id AND\n" +
                "        flights.flightid = schedule.flightid AND\n" +
                "        tickets.passengerid = passenger.passengerid AND\n" +
                "        flightstatus IN (4, 5) AND\n" +
                "        DATE(scheduleddeparture) = ?);";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerDateType.class), startDate);
    }

    public List<PassengerBaggageType> getPassengersByBaggage() {
        String query = "" +
                "SELECT *\n" +
                "FROM tickets, passenger, baggage\n" +
                "WHERE (baggage.passengerid = passenger.passengerid AND\n" +
                "       tickets.passengerid=  passenger.passengerid\n" +
                ");";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerBaggageType.class));
    }

    public List<PassengerType> getPassengersByAge(int age) {
        String query = "" +
                "SELECT DISTINCT *" +
                "FROM tickets, passenger\n" +
                "WHERE ( tickets.passengerid=  passenger.passengerid AND\n" +
                "        age > ?)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerType.class), age);
    }

    public List<PassengerType> getPassengersBySex(String sex) {
        String query = "" +
                "SELECT DISTINCT *" +
                "FROM tickets, passenger\n" +
                "WHERE ( tickets.passengerid=  passenger.passengerid AND\n" +
                "        sex = ?)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PassengerType.class), sex);
    }

}

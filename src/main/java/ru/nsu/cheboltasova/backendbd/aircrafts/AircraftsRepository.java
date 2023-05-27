package ru.nsu.cheboltasova.backendbd.aircrafts;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AircraftsRepository {
    private final JdbcTemplate jdbcTemplate;

    public AircraftsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getAircraftsID() {
        String query = "SELECT aircraftid FROM aircraft";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<String> getAirportsName() {
        String query = "SELECT airname FROM airport";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<AircraftsType> getAircrafts() {
        String query = "SELECT * FROM aircraft";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftsType.class));
    }

    public List<AircraftsType> getAircraftsByAirport(String airport) {
        String query = "" +
                "SELECT *\n" +
                "FROM  aircraft\n" +
                "WHERE airport = ?\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftsType.class), airport);
    }

    public List<AircraftAndCountType> getAircraftsByFlightsCount(int count) {
        String query = "" +
                "WITH numFlights AS (\n" +
                "    SELECT COUNT(aircraft.aircraftid) as count, aircraft.aircraftid as id\n" +
                "    FROM  aircraft, schedule, flights\n" +
                "    WHERE (\n" +
                "            flightstatus = 5 AND\n" +
                "            schedule.flightid = flights.flightid AND\n" +
                "            flights.aircraftid = aircraft.aircraftid)\n" +
                "    GROUP BY aircraft.aircraftid)\n" +
                "\n" +
                "SELECT aircraft.aircraftid, brigadepilotsid, brigadeserviceid, brigadetechid," +
                "aircrafttype, age, aircraft.numberofseats, airport, tankcapacity, numFlights.count as count\n" +
                "FROM   aircraft, numFlights\n" +
                "WHERE  (numFlights.id = aircraft.aircraftid AND numFlights.count = ?)\n" +
                "GROUP BY aircraft.aircraftid, numFlights.id, aircrafttype, numFlights.count;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftAndCountType.class), count);
    }

    public List<AircraftAndArrivalTimeType> getAircraftsByLandInAirport(String airport) {
        String query = "" +
                "SELECT *\n" +
                "FROM   schedule, flights, aircraft, airport\n" +
                "WHERE (schedule.flightid = flights.flightid AND\n" +
                "       flights.aircraftid = aircraft.aircraftid AND\n" +
                "       endpoint = airport.id AND\n" +
                "       flightstatus = 5 AND airport.airname = ?)\n" +
                "ORDER BY scheduledarrival;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftAndArrivalTimeType.class), airport);
    }

    public List<AircraftAndDepartureTimeType> getAircraftsInAirportByTime(String time, String airport) {
        LocalDate startDate = LocalDate.parse(time);
        LocalDate endDate = LocalDate.parse(time);
        String query = "" +
                "SELECT *\n" +
                "FROM   schedule, flights, aircraft, airport\n" +
                "WHERE (\n" +
                "       schedule.flightid = flights.flightid AND\n" +
                "       flights.aircraftid = aircraft.aircraftid AND\n" +
                "       airport.airname = ? AND\n" +
                "       ((startpoint = airport.id AND flightstatus IN (1, 2, 3) AND ? < scheduleddeparture) OR\n" +
                "       (endpoint    = airport.id AND flightstatus IN (5) AND ? > scheduledarrival))\n" +
                ");";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftAndDepartureTimeType.class), airport, startDate, endDate);
    }

    public List<AircraftsType> getAircraftsInAirportByTechInspect(String startTime, String endTime) {
        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);
        String query = "" +
                "SELECT DISTINCT aircraft.aircraftid, brigadepilotsid, brigadeserviceid, brigadetechid,\n" +
                " aircrafttype, age, aircraft.numberofseats, airport, tankcapacity\n" +
                "FROM  receivedservice, aircraft\n" +
                "WHERE (aircraft.aircraftid = receivedservice.aircraftid AND\n" +
                "    receivedservice.aircraftserviceid = 1 AND\n" +
                "    proceduredate BETWEEN ? AND ?\n" +
                ")";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftsType.class), startDate, endDate);
    }

    public List<AircraftsType> getAircraftsByRepairTime(String startTime, String endTime) {
        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);
        String query = "" +
                "SELECT DISTINCT aircraft.aircraftid, brigadepilotsid, brigadeserviceid, brigadetechid,\n" +
                " aircrafttype, age, aircraft.numberofseats, airport, tankcapacity\n" +
                "FROM  receivedservice, aircraft\n" +
                "WHERE (aircraft.aircraftid = receivedservice.aircraftid AND " +
                "    receivedservice.aircraftserviceid = 2 AND\n" +
                "    proceduredate BETWEEN ? AND ?\n" +
                ")";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftsType.class), startDate, endDate);
    }

    public List<AircraftAndCountServiceType> getAircraftsByRepairCount(int count) {
        String query = "" +
                "SELECT DISTINCT countofservice, receivedservice.aircraftid, brigadepilotsid, brigadeserviceid, brigadetechid," +
                " aircrafttype, age, aircraft.numberofseats, airport, tankcapacity\n" +
                "FROM  receivedservice, aircraft\n" +
                "WHERE (aircraft.aircraftid = receivedservice.aircraftid AND aircraftserviceid = 2 AND countofservice = ?)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftAndCountServiceType.class), count);
    }

    public List<AircraftAndCountType> getAircraftsByFlightCountBeforeRepair() {
        String query = "" +
                "SELECT COUNT(flights.flightid), aircraft.aircraftid, brigadepilotsid, brigadeserviceid, brigadetechid," +
                "aircrafttype, age, aircraft.numberofseats, airport, tankcapacity \n" +
                "FROM flights, aircraft, schedule, (\n" +
                "    SELECT aircraftid, proceduredate as repairDate\n" +
                "    FROM receivedservice\n" +
                "    WHERE aircraftserviceid = 2\n" +
                "  ) as lastRepair\n" +
                "WHERE (\n" +
                "       flights.aircraftid = aircraft.aircraftid AND\n" +
                "       lastRepair.aircraftid = flights.aircraftid AND\n" +
                "       schedule.flightid = flights.flightid AND\n" +
                "       scheduleddeparture < repairDate AND\n" +
                "       flightstatus != 2\n" +
                "    )\n" +
                "group by aircraft.aircraftid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftAndCountType.class));
    }

    public List<AircraftsType> getAircraftsByAge(int age) {
        String query = "" +
                "SELECT DISTINCT *\n" +
                "FROM  aircraft\n" +
                "WHERE age = ?\n" +
                "ORDER BY age";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AircraftsType.class), age);
    }

    public void createAircarft(AircraftsType aircraft) {
        String getLastIdQuery = "SELECT MAX(aircraftid) FROM aircraft";
        Integer lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class) + 1;

        String query = "" +
                "INSERT INTO aircraft (aircraftid, brigadepilotsid, brigadetechid, brigadeserviceid, aircrafttype, age, numberofseats, airport, tankcapacity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, lastId, aircraft.getBrigadePilotsID(), aircraft.getBrigadeTechID(),
                aircraft.getBrigadeServiceID(), aircraft.getAircraftType(), aircraft.getAge(),
                aircraft.getNumberOfSeats(), aircraft.getAirport(), aircraft.getTankCapacity());
    }

    public void updateAircraft(AircraftsType aircraft) {
        String query = "UPDATE aircraft SET brigadepilotsid = ?, brigadetechid = ?, brigadeserviceid = ?, " +
                "aircrafttype = ?, age = ?, numberofseats = ?, airport = ?, tankcapacity = ? WHERE aircraftid = ?";
        jdbcTemplate.update(query, aircraft.getAircraftID(), aircraft.getBrigadePilotsID(), aircraft.getBrigadeTechID(),
                aircraft.getBrigadeServiceID(), aircraft.getAircraftType(), aircraft.getAge(),
                aircraft.getNumberOfSeats(), aircraft.getAirport(), aircraft.getTankCapacity());
    }

    public void deleteAircraft(int id) {
        String query = "DELETE FROM aircraft WHERE aircraftid = ?";
        jdbcTemplate.update(query, id);
    }
}

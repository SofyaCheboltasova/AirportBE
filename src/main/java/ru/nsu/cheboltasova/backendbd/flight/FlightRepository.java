package ru.nsu.cheboltasova.backendbd.flight;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepository {
    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getFlights() {
        String query = "SELECT DISTINCT flightid FROM flights ORDER BY flightid";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<FlightType> getFullFlights() {
        String query = "" +
                "SELECT DISTINCT flights.*, flighttype.flighttype, aircrafttype " +
                "FROM flights, flighttype, aircraft " +
                "WHERE (flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id) " +
                "ORDER BY flightid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class));
    }

    public List<FlightType> getFlightsByRoute(String start, String end) {
        String query = "" +
                "SELECT *\n" +
                "FROM   flights, flighttype, aircraft, schedule, airport, airport as airport1\n" +
                "WHERE  (startpoint = airport.id AND endpoint = airport1.id AND\n" +
                "airport.airlocation = ? AND airport1.airlocation = ? AND flights.aircraftid = aircraft.aircraftid " +
                "AND flights.flighttypeid = flighttype.id AND schedule.flightid = flights.flightid)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), start, end);
    }

    public List<FlightType> getFlightsByTicketCost() {
        String query = "" +
                "SELECT *\n" +
                "FROM   flights, flighttype, aircraft\n" +
                "WHERE (defaultticketcost IS NOT NULL AND flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id)\n" +
                "ORDER BY defaultticketcost DESC";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class));
    }

    public List<FlightDurationType> getFlightsByDuration() {
        String query = "" +
                "SELECT *, (scheduledarrival - scheduleddeparture) as flightDuration\n" +
                "FROM  schedule, flights, aircraft, flighttype\n" +
                "WHERE (schedule.flightid = flights.flightid AND flights.aircraftid = aircraft.aircraftid " +
                "AND flights.flighttypeid = flighttype.id)\n" +
                "ORDER BY flightDuration DESC;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightDurationType.class));
    }

    public List<FlightDurationType> getFlightsByAllParameters(String start, String end) {
        String query = "" +
                "SELECT *, (scheduledarrival - scheduleddeparture) as flightDuration\n" +
                "\n" +
                "FROM   flights, schedule, airport, airport as airport1, aircraft\n" +
                "WHERE  (" +
                "schedule.flightid = flights.flightid AND flights.aircraftid = aircraft.aircraftid AND\n" +
                "schedule.startpoint = airport.id AND schedule.endpoint = airport1.id AND\n" +
                "schedule.flightid = flights.flightid AND\n" +
                "airport.airlocation = ? AND\n" +
                "airport1.airlocation = ? AND\n" +
                "defaultticketcost IS NOT NULL\n" +
                ");";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightDurationType.class), start, end);
    }


    public List<FlightType> getCancelledFlights() {
        String query = "" +
                "SELECT DISTINCT flights.*, flighttype.flighttype, aircrafttype\n" +
                "FROM   flightstatus, schedule, flights, flighttype, aircraft\n" +
                "WHERE  (schedule.flightstatus = flightstatus.id AND flightstatus.flightstatus = 'Отменен' AND\n " +
                "flights.flightid = schedule.flightid AND flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id)\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class));
    }

    public List<FlightType> getCancelledFlightsByStartPoint(String start) {
        String query = "" +
                "SELECT *\n" +
                "FROM  flights, schedule, airport, flightstatus, aircraft, flighttype\n" +
                "WHERE  (schedule.flightstatus = flightstatus.id AND flightstatus.flightstatus = 'Отменен' AND\n" +
                " endpoint = airport.id AND airport.airlocation = ? AND " +
                "flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id AND schedule.flightid = flights.flightid)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), start);
    }

    public List<FlightType> getCancelledFlightsByRoute(String start, String end) {
        String query = "" +
                "SELECT *\n" +
                "FROM   flights, flightstatus, flighttype, aircraft, schedule, airport, airport as airport1\n" +
                "WHERE  (startpoint = airport.id AND endpoint = airport1.id AND\n" +
                "        schedule.flightstatus = flightstatus.id AND flightstatus.flightstatus = 'Отменен' AND\n" +
                "        airport.airlocation = ? AND airport1.airlocation = ? AND\n" +
                "        flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id AND schedule.flightid = flights.flightid)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), start, end);
    }

    public List<FlightFreeSeatsType> getFlightsByFreeSeatsCount() {
        String query = "" +
                "SELECT *,\n" +
                "       flights.numberofseats - t.numTickets as freeSeats\n" +
                "FROM flights, schedule, aircraft, flighttype, (\n" +
                "        SELECT flightid, COUNT(*) AS numTickets\n" +
                "        FROM Tickets\n" +
                "        GROUP BY flightid) as t\n" +
                "WHERE (schedule.flightid = flights.flightid AND schedule.flightid = t.flightid AND flightstatus = 2 AND\n" +
                "       flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightFreeSeatsType.class));
    }

    public List<FlightPercentageType> getFlightsByPercentageSeatsCount() {
        String query = "" +
                "SELECT *,\n" +
                "       flights.numberofseats - t.numTickets as freeSeats,\n" +
                "       (flights.numberofseats - t.numTickets) * 100 / flights.numberofseats as percentageCount\n" +
                "FROM flights, schedule, aircraft, flighttype,(\n" +
                "        SELECT flightid, COUNT(*) AS numTickets\n" +
                "        FROM Tickets\n" +
                "        GROUP BY flightid) as t\n" +
                "WHERE (schedule.flightid = flights.flightid AND schedule.flightid = t.flightid AND flightstatus = 2 AND\n" +
                "       flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightPercentageType.class));
    }



    public List<FlightType> getDelayedFlights() {
        String query = "" +
                "SELECT DISTINCT flights.*, flighttype.flighttype, aircrafttype\n" +
                "FROM   flightstatus, schedule, flights, flighttype, aircraft\n" +
                "WHERE  (schedule.flightstatus = flightstatus.id AND delayreason IS NOT NULL AND\n " +
                "flights.flightid = schedule.flightid AND flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id)\n" +
                "ORDER BY flights.flightid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class));
    }

    public List<FlightType> getDelayedFlightsByReason(int id) {
        String query = "" +
                "SELECT DISTINCT flights.*, flighttype.flighttype, aircrafttype\n" +
                "FROM  schedule, delayreason, flights, aircraft, flighttype\n" +
                "WHERE  schedule.delayreason is not null\n" +
                "       AND schedule.delayreason = delayreason.id\n" +
                "       AND delayreason.id = ?" +
                "       AND flights.flightid = schedule.flightid AND flights.aircraftid = aircraft.aircraftid " +
                "       AND flights.flighttypeid = flighttype.id";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), id);
    }

    public List<FlightType> getDelayedFlightsByRoute(String start, String end) {
        String query = "" +
                "SELECT *\n" +
                "FROM   flights, flightstatus, flighttype, aircraft, schedule, airport, airport as airport1\n" +
                "WHERE  (startpoint = airport.id AND endpoint = airport1.id AND\n" +
                "        schedule.flightstatus = flightstatus.id AND delayreason is not null AND\n" +
                "        airport.airlocation = ? AND airport1.airlocation = ? AND\n" +
                "        flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id AND schedule.flightid = flights.flightid)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), start, end);
    }

    public int getDelayedFlightsByReturnedTickets() {
        String query = "" +
                "SELECT COUNT(DISTINCT tickets.passengerid)\n" +
                "FROM   schedule, tickets, ticketstatus\n" +
                "WHERE  (\n" +
                "        schedule.flightid = tickets.flightid AND\n" +
                "        delayreason is not null AND\n" +
                "        status = ticketstatus.id AND ticketstatus = 'Возвращен' AND\n" +
                "        returntime <= actualdeparture  AND returntime >= scheduleddeparture\n" +
                ")";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<String> getDelayReasons() {
        String query = "" +
                "SELECT delayreason.delayreason\n" +
                "FROM   delayreason\n" +
                "ORDER BY id";
        return jdbcTemplate.queryForList(query, String.class);
    }


    public List<String> getAircraftType() {
        String query = "" +
                "SELECT DISTINCT aircrafttype\n" +
                "FROM   aircraft\n" +
                "ORDER BY aircrafttype";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<String> getFlightType() {
        String query = "" +
                "SELECT DISTINCT flighttype.flighttype\n" +
                "FROM   flighttype\n" +
                "ORDER BY flighttype";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<FlightType> getFlightsByAircraftType(String type) {
        String query = "" +
                "SELECT DISTINCT flights.*, flighttype.flighttype, aircrafttype " +
                "FROM flights, flighttype, aircraft " +
                "WHERE (" +
                "flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id\n" +
                "AND flights.aircraftid = aircraft.aircraftid AND aircraft.aircrafttype = ?) " +
                "ORDER BY flightid";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), type);
    }

    public int getAvgSoldTicketsByRoute(String start, String end) {
        String query = "" +
                "SELECT ROUND(AVG(soldTickets)) as average_tickets_sold\n" +
                "FROM (\n" +
                "  SELECT COUNT(*) as soldTickets, schedule.flightid\n" +
                "  FROM tickets, schedule, airport, airport as airport1\n" +
                "  WHERE (tickets.flightid = schedule.flightid AND\n" +
                "         startpoint = airport.id AND endpoint = airport1.id AND\n" +
                "         airport.airlocation = ? AND airport1.airlocation = ?\n" +
                "         )\n" +
                "  GROUP BY schedule.flightid\n" +
                ") as t";
        return jdbcTemplate.queryForObject(query, Integer.class, start, end);
    }

    public List<FlightTimeType> getFlightsByDepartureTime() {
        String query = "" +
                "SELECT *\n" +
                "FROM   flights, flighttype, aircraft, schedule\n" +
                "WHERE (flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id AND\n" +
                "schedule.flightid = flights.flightid)\n" +
                "ORDER BY scheduleddeparture DESC";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightTimeType.class));
    }

    public List<FlightType> getFlightsByFlightType(String id) {
        String query = "" +
                "SELECT *\n" +
                "FROM  flights, flighttype, aircraft\n" +
                "WHERE (flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id AND\n" +
                "       flighttype.flighttype = ?)\n" +
                "ORDER BY flights.flightid DESC;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), id);
    }

    public List<FlightType> getFlightsByStartPoint(String start) {
        String query = "" +
                "SELECT *\n" +
                "FROM  flights, airport, schedule, flightstatus, aircraft, flighttype\n" +
                "WHERE  (schedule.flightstatus = flightstatus.id AND endpoint = airport.id AND airport.airlocation = ? AND " +
                "flights.aircraftid = aircraft.aircraftid AND flights.flighttypeid = flighttype.id " +
                "AND schedule.flightid = flights.flightid)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FlightType.class), start);
    }
}
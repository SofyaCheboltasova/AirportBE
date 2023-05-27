package ru.nsu.cheboltasova.backendbd.tickets;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TicketsRpository {
    private final JdbcTemplate jdbcTemplate;
    public TicketsRpository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getTicketsID() {
        String query = "SELECT ticketid FROM tickets";
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    public List<TicketsType> getTickets() {
        String query = "SELECT * FROM tickets";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TicketsType.class));
    }

    public void createTicket(TicketsType ticket) {
        String getLastIdQuery = "SELECT MAX(ticketid) FROM tickets";
        int lastId = jdbcTemplate.queryForObject(getLastIdQuery, Integer.class);
        int newId = lastId + 1;

        String createQuery = "INSERT INTO tickets (ticketid, passengerid, flightid, ticketcost, seat, ordertype, status, ordertime, returntime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(createQuery, newId, ticket.getPassengerID(), ticket.getFlightID(), ticket.getTicketCost(), ticket.getSeat(), ticket.getOrderType(), ticket.getStatus(), ticket.getOrderTime(), ticket.getReturnTime());
    }


    public void updateTicket(TicketsType ticket) {
        String query = "UPDATE Tickets SET PassengerID = ?, FlightID = ?, TicketCost = ?, Seat = ?, OrderType = ?, Status = ?, OrderTime = ?, ReturnTime = ? WHERE TicketID = ?";
        jdbcTemplate.update(query, ticket.getPassengerID(), ticket.getFlightID(), ticket.getTicketCost(), ticket.getSeat(), ticket.getOrderType(), ticket.getStatus(), ticket.getOrderTime(), ticket.getReturnTime(), ticket.getTicketID());
    }

    public void deleteTicket(int ticketID) {
        String query = "DELETE FROM tickets WHERE ticketid = ?";
        jdbcTemplate.update(query, ticketID);
    }


    public List<String> getCities() {
        String query = "SELECT airlocation FROM airport";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public int getReturnedTicketsCountByFlight(int flightID) {
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM tickets, flights, returned\n" +
                "WHERE (tickets.ticketid = returned.ticketid AND\n" +
                "       tickets.flightid = ?)";
        Integer result = jdbcTemplate.queryForObject(query, new Object[]{flightID}, Integer.class);
        return result != null ? result : 0;
    }

    public int getReturnedTicketsCountByDay(String time) {
        LocalDate startDate = LocalDate.parse(time);
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM tickets, flights, returned\n" +
                "WHERE ( tickets.ticketid = returned.ticketid AND\n" +
                "        DATE(returntime) = ?)";
        return jdbcTemplate.queryForObject(query, Integer.class, startDate);
    }

    public int getReturnedTicketsCountByCost(int cost) {
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM tickets, flights, returned\n" +
                "WHERE ( tickets.ticketid = returned.ticketid AND\n" +
                "        ticketcost > ?)";
        return jdbcTemplate.queryForObject(query, Integer.class, cost);
    }

    public int getReturnedTicketsCountByRoute(String start, String end) {
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM   tickets, flights, returned, schedule, airport, airport as airport1\n" +
                "WHERE (\n" +
                "        tickets.flightid = schedule.flightid AND\n" +
                "        tickets.ticketid = returned.ticketid AND\n" +
                "        startpoint = airport.id AND\n" +
                "        endpoint = airport1.id AND\n" +
                "        airport.airlocation = ? AND airport1.airlocation = ?)";
        return jdbcTemplate.queryForObject(query, Integer.class, start, end);
    }

    public int getReturnedTicketsCountByAge(int age) {
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM tickets, returned, passenger\n" +
                "WHERE ( tickets.ticketid = returned.ticketid AND\n" +
                "        tickets.passengerid = passenger.passengerid AND\n" +
                "        age > ?)";
        return jdbcTemplate.queryForObject(query, Integer.class, age);
    }

    public int getReturnedTicketsCountBySex(String sex) {
        String query = "" +
                "WITH returned AS (\n" +
                "    SELECT ticketid\n" +
                "    FROM tickets\n" +
                "    WHERE status = 2\n" +
                ")\n" +
                "\n" +
                "SELECT COUNT(DISTINCT tickets.ticketid)\n" +
                "FROM tickets, returned, passenger\n" +
                "WHERE ( tickets.ticketid = returned.ticketid AND\n" +
                "        tickets.passengerid = passenger.passengerid AND\n" +
                "        sex = ?)";
        return jdbcTemplate.queryForObject(query, Integer.class, sex);
    }

    public int getSoldTicketsAvgCountByRoute(String start, String end) {
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
}

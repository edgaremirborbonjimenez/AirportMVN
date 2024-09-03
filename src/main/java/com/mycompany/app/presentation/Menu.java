package presentation;

import airport.*;
import implementation.*;
import interfaces.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private IAirportDAO airportDAO;
    private IFlightDAO flightDAO;
    private ICityDAO cityDAO;
    private ITicketDAO ticketDAO;
    private ISeatDAO seatDAO;
    private IPassengerDAO passengerDAO;

    public Menu() {
        airportDAO = AirportDAO.getInstance();
        flightDAO = FlightDAO.getInstance();
        cityDAO = CityDAO.getInstance();
        ticketDAO= TicketDAO.getInstance();
        seatDAO = SeatDAO.getInstance();
        passengerDAO = PassengerDAO.getInstance();

    }

    static {
        System.out.println("Welcome to Airport Menu");
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1.- Find a flights to go to somewhere");
        System.out.println("2.- See my tickets");
        System.out.println("Press 3 to exit");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("!!!Enter valid option");
            //System.out.println("What do you want to do?");
            //System.out.println("1. Find a flights to go to somewhere");
            //System.out.println("Press 3 to exit");
        }
        int option = scanner.nextInt();
        if (option == 1) {
            selectDestination();
        }else if(option == 2){
            seeMyTickets();
        }else if(option == 3){
            return;
        }else{
            System.out.println("Please select a valid option");
            displayMenu();
        }
    }

    public void selectDestination(){
        try {
            System.out.println("Select where you are leaving");
            for (int i = 0; i < cityDAO.getCities().size(); i++) {
                System.out.println((i + 1) + ".-" + cityDAO.getCities().get(i).getName());
            }
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("!!!Enter valid option");
                scanner.next();
            }
            int optionLeaving = scanner.nextInt();
            if (optionLeaving > cityDAO.getCities().size()) {
                System.out.println("Invalid option");
                selectDestination();
            } else {
                System.out.println("Select destination");
                for (int i = 0; i < cityDAO.getCities().size(); i++) {
                    System.out.println((i + 1) + ".-" + cityDAO.getCities().get(i).getName());
                }
                while (!scanner.hasNextInt()) {
                    System.out.println("!!!Enter valid option");
                    scanner.next();
                }
                int optionGoingTo = scanner.nextInt();
                if (optionGoingTo > cityDAO.getCities().size()) {
                    System.out.println("Invalid option");
                    selectDestination();
                } else {
                    if (optionLeaving == optionGoingTo) {
                        System.out.println("Please select a destination different of the leaving place");
                        selectDestination();
                    } else {
                        showRoutes(flightDAO.getRoute(cityDAO.getCities().get(optionLeaving - 1), cityDAO.getCities().get(optionGoingTo - 1)));
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage()+ " Talk to the IT Department");
            displayMenu();
        }
    }
    public void showRoutes(List<List<Flight>> routes)throws Exception{
        for(int i = 0;i<routes.size();i++){
            System.out.println("Route"+ (i+1));
            int cont = routes.get(i).size();
            System.out.println("Flights needed to take");
            for(Flight flight : routes.get(i)){
                System.out.println("Flight #"+cont+" Leave: "+flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName()+" -- Price: "+ flight.getPrice());
                cont--;
            }
            System.out.println("Price for the complete route: "+ flightDAO.getRoutePrice(routes.get(i)));
            System.out.println("---------------------------");
        }
        System.out.println("----Select a route----");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("!!!Enter valid option");
            scanner.next();
        }
        int option = scanner.nextInt();
        if(option>routes.size() || option<0){
            System.out.println("Please select a valid option");
            showRoutes(routes);
        }
        chooseRoute(option,routes);
    }

    public void chooseRoute(int routeIndex,List<List<Flight>> routes) throws Exception{
        int cont=1;
        for(Flight flight : routes.get(routeIndex-1)){
                System.out.println("Flight #"+ (cont) +" Leave: "+flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName()+" -- Price: "+ flight.getPrice());
            cont++;
        }
        System.out.println("Price for the complete route: "+ flightDAO.getRoutePrice(routes.get(routeIndex-1)));

        Scanner scanner = new Scanner(System.in);
        System.out.println("---------Options---------");
        System.out.println("1.- Buy All the Route");
        System.out.println("2 .- Buy one flight");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("!!!Enter valid option");
        }
        int option = scanner.nextInt();
        if (option == 1) {
            buyAllRouteTickets(routes.get(routeIndex-1));
        }if(option==2){
            chooseFlight(routes.get(routeIndex-1));
        }else{
            System.out.println("Select a valid option");
            chooseRoute(routeIndex,routes);
        }
    }

    public void chooseFlight(List<Flight> flights){
        Scanner scanner = new Scanner(System.in);
        int cont=1;
        for(Flight flight : flights){
            System.out.println("Flight #"+ (cont) +" Leave: "+flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName()+" -- Price: "+ flight.getPrice());
            cont++;
        }
        System.out.println("Select a flight");
        int option = scanner.nextInt();
        while (!scanner.hasNextInt()) {
            System.out.println("!!!Enter valid option");
            scanner.next();
        }
        if(option>flights.size() || option<0){
            System.out.println("Please select a valid option");
            chooseFlight(flights);
        }
        buyFlight(option,flights);
    }

    public void buyFlight(int flightIndex,List<Flight> route) {
        if(route.get(flightIndex-1).getPrice()> passengerDAO.getPassegerInfo().getMoney()){
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
        }
        Seat s = seatDAO.asignSeat(route.get(flightIndex-1));
        Ticket t = ticketDAO.buyFlightTicket(route.get(flightIndex-1), s);
        System.out.println("This ticket was bought");
        System.out.println(t);
        returnMenu();
    }

    public void buyAllRouteTickets(List<Flight> route) {
        double total = flightDAO.getRoutePrice(route);
        if(total>passengerDAO.getPassegerInfo().getMoney()){
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
        }
        List<Seat> seats = new LinkedList<>();
        for(Flight f: route){
            Seat s =seatDAO.asignSeat(f);
            seats.add(s);
        }
        List<Ticket> tickets = ticketDAO.buyRouteTickets(route,seats);
        System.out.println("Tickets bought successfully");
        returnMenu();

    }

    public void returnMenu() {
        System.out.println("Press  any number to return to the menu");
        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()){
            System.out.println("!!!Enter valid option");
            scanner.next();
        }
        displayMenu();
    }

    public void seeMyTickets(){
        if(passengerDAO.getPassegerInfo().getTicket().size()==0){
            System.out.println("You dont have tickets");
        }
        for(Ticket t: passengerDAO.getPassegerInfo().getTicket()){
            System.out.println("Leaving: "+t.getFlight().getLeaving().getName());
            System.out.println("GoingTo: "+t.getFlight().getGoingTo().getName());
            System.out.println("Seat number: "+t.getSeat().getNumber());
            System.out.println("----------------------------------------------------");
        }
        returnMenu();
    }
}

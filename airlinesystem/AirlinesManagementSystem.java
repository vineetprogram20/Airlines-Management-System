package airlinesystem;

import java.util.ArrayList;
import java.util.Scanner;

// Flight class to store flight details
class Flight {
    String flightNumber;
    String origin;
    String destination;
    int capacity;
    int bookedSeats;
    double price;

    public Flight(String flightNumber, String origin, String destination, int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.bookedSeats = 0;
        this.price = price;
    }
}

// Passenger class to store passenger details
class Passenger {
    String name;
    String passportNumber;
    String flightNumber;

    public Passenger(String name, String passportNumber, String flightNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.flightNumber = flightNumber;
    }
}

// Main Airlines Management System class
public class AirlinesManagementSystem {
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Airlines Management System ===");
            System.out.println("1. Add Flight");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Flights");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewFlights();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using Airlines Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addFlight() {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter capacity: ");
        int capacity = readInt();
        System.out.print("Enter price: ");
        double price = readDouble();

        flights.add(new Flight(flightNumber, origin, destination, capacity, price));
        System.out.println("Flight added successfully!");
    }

    private static void bookTicket() {
        viewFlights();
        if (flights.isEmpty()) {
            return;
        }

        System.out.print("Enter flight number to book: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.flightNumber.equals(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        if (selectedFlight.bookedSeats >= selectedFlight.capacity) {
            System.out.println("Sorry, flight is full!");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();
        System.out.print("Enter passport number: ");
        String passportNumber = scanner.nextLine();

        passengers.add(new Passenger(name, passportNumber, flightNumber));
        selectedFlight.bookedSeats++;
        System.out.println("Ticket booked successfully!");
    }

    private static void viewFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available!");
            return;
        }

        System.out.println("\nAvailable Flights:");
        System.out.println("Flight\tFrom\tTo\tAvailable\tPrice");
        for (Flight flight : flights) {
            int availableSeats = flight.capacity - flight.bookedSeats;
            System.out.println(flight.flightNumber + "\t" + flight.origin + "\t" +
                    flight.destination + "\t" + availableSeats + "\t\t" + flight.price);
        }
    }

    private static void viewBookings() {
        if (passengers.isEmpty()) {
            System.out.println("No bookings available!");
            return;
        }

        System.out.println("\nBookings:");
        System.out.println("Name\tPassport\tFlight");
        for (Passenger passenger : passengers) {
            System.out.println(passenger.name + "\t" + passenger.passportNumber + "\t" +
                    passenger.flightNumber);
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return result;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return result;
    }
}

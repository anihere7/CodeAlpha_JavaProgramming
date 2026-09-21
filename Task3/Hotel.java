import java.io.*;
import java.util.*;

public class Hotel {

    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    private static final String FILE_NAME = "reservations.txt";

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        initializeRooms();
        loadReservations();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Standard", 2500));
        rooms.add(new Room(102, "Standard", 2500));
        rooms.add(new Room(103, "Standard", 2500));

        rooms.add(new Room(201, "Deluxe", 4000));
        rooms.add(new Room(202, "Deluxe", 4000));
        rooms.add(new Room(203, "Deluxe", 4000));

        rooms.add(new Room(301, "Suite", 6500));
        rooms.add(new Room(302, "Suite", 6500));
    }

    public void displayRooms() {
        System.out.println("\n================ HOTEL ROOMS ================");

        System.out.printf(
                "%-10s %-15s %-15s %-12s%n",
                "Room No.", "Category", "Price/Night", "Status"
        );

        System.out.println("----------------------------------------------");

        for (Room room : rooms) {
            room.displayRoom();
        }

        System.out.println("==============================================");
    }

    public void searchRooms(String category) {
        boolean found = false;

        System.out.println("\n========== AVAILABLE " + category.toUpperCase() + " ROOMS ==========");

        System.out.printf(
                "%-10s %-15s %-15s%n",
                "Room No.", "Category", "Price/Night"
        );

        System.out.println("------------------------------------------");

        for (Room room : rooms) {
            if (room.isAvailable()
                    && room.getCategory().equalsIgnoreCase(category)) {

                System.out.printf(
                        "%-10d %-15s ₹%-14.2f%n",
                        room.getRoomNumber(),
                        room.getCategory(),
                        room.getPricePerNight()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms found.");
        }

        System.out.println("==========================================");
    }

    public Room findAvailableRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber
                    && room.isAvailable()) {
                return room;
            }
        }

        return null;
    }

    public Reservation createReservation(
            String guestName,
            int roomNumber,
            String checkInDate,
            String checkOutDate,
            int nights) {

        Room room = findAvailableRoom(roomNumber);

        if (room == null) {
            System.out.println("Room is unavailable or does not exist.");
            return null;
        }

        if (nights <= 0) {
            System.out.println("Number of nights must be greater than zero.");
            return null;
        }

        double totalAmount =
                room.getPricePerNight() * nights;

        System.out.printf(
                "\nTotal booking amount: ₹%.2f%n",
                totalAmount
        );

        if (!Payment.processPayment(totalAmount)) {
            return null;
        }

        String reservationId =
                "RES" + (1000 + reservations.size() + 1);

        Reservation reservation =
                new Reservation(
                        reservationId,
                        guestName,
                        room.getRoomNumber(),
                        room.getCategory(),
                        checkInDate,
                        checkOutDate,
                        totalAmount,
                        "PAID"
                );

        room.setAvailable(false);
        reservations.add(reservation);

        saveReservations();

        System.out.println(
                "\nReservation created successfully!"
        );

        return reservation;
    }

    public boolean cancelReservation(String reservationId) {

        Reservation reservation = findReservation(reservationId);

        if (reservation == null) {
            return false;
        }

        for (Room room : rooms) {
            if (room.getRoomNumber() == reservation.getRoomNumber()) {
                room.setAvailable(true);
                break;
            }
        }

        reservations.remove(reservation);
        saveReservations();

        return true;
    }

    public Reservation findReservation(String reservationId) {

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId()
                    .equalsIgnoreCase(reservationId)) {

                return reservation;
            }
        }

        return null;
    }

    public void displayAllReservations() {

        System.out.println("\n========== ALL RESERVATIONS ==========");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {

            for (Reservation reservation : reservations) {
                reservation.displayReservation();
            }
        }

        System.out.println("=======================================");
    }

    private void saveReservations() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(FILE_NAME))) {

            for (Reservation reservation : reservations) {
                writer.write(reservation.toFileString());
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving reservations: "
                            + e.getMessage()
            );
        }
    }

    private void loadReservations() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length != 8) {
                    continue;
                }

                String reservationId = data[0];
                String guestName = data[1];
                int roomNumber = Integer.parseInt(data[2]);
                String category = data[3];
                String checkIn = data[4];
                String checkOut = data[5];
                double amount = Double.parseDouble(data[6]);
                String paymentStatus = data[7];

                Reservation reservation =
                        new Reservation(
                                reservationId,
                                guestName,
                                roomNumber,
                                category,
                                checkIn,
                                checkOut,
                                amount,
                                paymentStatus
                        );

                reservations.add(reservation);

                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        room.setAvailable(false);
                        break;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading reservations: "
                            + e.getMessage()
            );
        }
    }
}
import java.util.Scanner;

public class HotelReservationSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Hotel hotel = new Hotel();

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("       HOTEL RESERVATION SYSTEM");
        System.out.println("==========================================");

        int choice;

        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    hotel.displayRooms();
                    break;

                case 2:
                    searchRooms();
                    break;

                case 3:
                    makeReservation();
                    break;

                case 4:
                    cancelReservation();
                    break;

                case 5:
                    viewReservation();
                    break;

                case 6:
                    hotel.displayAllReservations();
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using Hotel Reservation System!"
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n============== MAIN MENU ==============");
        System.out.println("1. View All Rooms");
        System.out.println("2. Search Available Rooms");
        System.out.println("3. Make Reservation");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. View Booking Details");
        System.out.println("6. View All Reservations");
        System.out.println("7. Exit");
        System.out.println("========================================");
    }

    private static void searchRooms() {

        System.out.println("\nRoom Categories:");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        if (category.equals("1")) {
            category = "Standard";
        } else if (category.equals("2")) {
            category = "Deluxe";
        } else if (category.equals("3")) {
            category = "Suite";
        }

        hotel.searchRooms(category);
    }

    private static void makeReservation() {

        System.out.println("\n========== MAKE RESERVATION ==========");

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        hotel.displayRooms();

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter check-in date (DD-MM-YYYY): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter check-out date (DD-MM-YYYY): ");
        String checkOutDate = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();
        scanner.nextLine();

        Reservation reservation =
                hotel.createReservation(
                        guestName,
                        roomNumber,
                        checkInDate,
                        checkOutDate,
                        nights
                );

        if (reservation != null) {
            reservation.displayReservation();
        }
    }

    private static void cancelReservation() {

        System.out.println("\n========== CANCEL RESERVATION ==========");

        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();

        if (hotel.cancelReservation(reservationId)) {

            System.out.println(
                    "Reservation "
                            + reservationId
                            + " cancelled successfully."
            );

        } else {

            System.out.println(
                    "Reservation not found."
            );
        }
    }

    private static void viewReservation() {

        System.out.println("\n========== BOOKING DETAILS ==========");

        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();

        Reservation reservation =
                hotel.findReservation(reservationId);

        if (reservation != null) {1
            reservation.displayReservation();
        } else {
            System.out.println("Reservation not found.");
        }
    }
}
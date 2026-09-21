public class Reservation {
    private String reservationId;
    private String guestName;
    private int roomNumber;
    private String roomCategory;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;
    private String paymentStatus;

    public Reservation(
            String reservationId,
            String guestName,
            int roomNumber,
            String roomCategory,
            String checkInDate,
            String checkOutDate,
            double totalAmount,
            String paymentStatus) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayReservation() {
        System.out.println("\n========== BOOKING DETAILS ==========");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest Name     : " + guestName);
        System.out.println("Room Number    : " + roomNumber);
        System.out.println("Room Category  : " + roomCategory);
        System.out.println("Check-in Date  : " + checkInDate);
        System.out.println("Check-out Date : " + checkOutDate);
        System.out.printf("Total Amount   : ₹%.2f%n", totalAmount);
        System.out.println("Payment Status : " + paymentStatus);
        System.out.println("=====================================");
    }

    public String toFileString() {
        return reservationId + "|" +
                guestName + "|" +
                roomNumber + "|" +
                roomCategory + "|" +
                checkInDate + "|" +
                checkOutDate + "|" +
                totalAmount + "|" +
                paymentStatus;
    }
}
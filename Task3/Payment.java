import java.util.Random;

public class Payment {

    public static boolean processPayment(double amount) {
        System.out.printf("\nProcessing payment of ₹%.2f...%n", amount);

        Random random = new Random();
        boolean successful = random.nextInt(10) < 9;

        if (successful) {
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed. Please try again.");
        }

        return successful;
    }
}
public class MobilePayment implements PaymentMethod {

    private String mobileNumber;
    private double simulatedBalance;

    public MobilePayment(double simulatedBalance) {

        this.simulatedBalance = simulatedBalance;
    }

    public MobilePayment(String mobileNumber,
                         double simulatedBalance) {

        this.mobileNumber = mobileNumber;
        this.simulatedBalance = simulatedBalance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getSimulatedBalance() {
        return simulatedBalance;
    }

    @Override
    public boolean pay(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > simulatedBalance) {
            return false;
        }

        simulatedBalance -= amount;

        return true;
    }
}
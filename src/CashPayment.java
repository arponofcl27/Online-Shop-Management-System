public class CashPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {

        return amount > 0;
    }
}
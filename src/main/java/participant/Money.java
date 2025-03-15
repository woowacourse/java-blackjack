package participant;

public class Money {

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public Money add(int amount) {
        return new Money(this.amount + amount);
    }

    public Money minus(int amount) {
        return new Money(this.amount - amount);
    }

    public Money multiply(double rate) {
        return new Money((int) (this.amount * rate));
    }

    public int getAmount() {
        return amount;
    }
}

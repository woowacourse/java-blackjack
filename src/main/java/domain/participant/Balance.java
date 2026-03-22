package domain.participant;

public class Balance {
    public static final Balance ZERO = new Balance(0);

    private final int balance;

    public Balance(int balance) {
        this.balance = balance;
    }

    public int getBalance(){
        return balance;
    }
}

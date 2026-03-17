package domain.participant;

public class Balance {
    public static final Balance ZERO = new Balance(0);

    private final int balance;

    public Balance(int balance) {
        this.balance = balance;
    }

    public static Balance zero(){
        return ZERO;
    }

    public Balance adjust(int betting){
        return new Balance(betting + balance);
    }

    public int getBalance(){
        return balance;
    }
}

package domain.player;

public class Betting {
    private final int amount;

    public Betting(int amount) {
        validate();
        this.amount = amount;
    }

    private void validate(){

    }

    public int getAmount(){
        return amount;
    }
}

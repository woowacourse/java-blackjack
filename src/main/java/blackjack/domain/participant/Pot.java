package blackjack.domain.participant;

public class Pot {
    private final int amount;

    public Pot(String amount) {
        this.amount = Integer.parseInt(amount);
    }

    public int getAmount() {
        return amount;
    }
}

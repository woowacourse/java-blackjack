package blackjack.domain.participant;

public class Player extends Participant {

    private Amount amount;

    public Player(String name) {
        super(name);
        amount = new Amount(0);
    }

    public void bet(int amount) {
        this.amount = this.amount.updateAmount(amount);
    }

    public Amount getAmount() {
        return amount;
    }
}

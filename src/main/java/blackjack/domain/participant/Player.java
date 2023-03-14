package blackjack.domain.participant;

public class Player extends Participant {

    private BettingAmount bettingAmount;

    public Player(String name) {
        super(name);
        bettingAmount = new BettingAmount(0);
    }

    public void bet(int amount) {
        this.bettingAmount = this.bettingAmount.updateBettingAmount(amount);
    }

    public BettingAmount getAmount() {
        return bettingAmount;
    }
}

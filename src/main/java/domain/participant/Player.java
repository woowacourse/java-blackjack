package domain.participant;

public class Player extends Participant {
    private final BettingAmount bettingAmount;

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean isNotFinished() {
        return !getCards().isBust() && !getCards().isBlackjack();
    }
}

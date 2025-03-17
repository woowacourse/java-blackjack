package domain.participant;

import domain.card.Card;
import domain.result.BlackjackResult;

public class Player extends Participant {

    private final String name;
    private final Money betAmount;

    private Player(final String name, final Money betAmount) {
        super();
        this.name = name;
        this.betAmount = betAmount;
    }

    public static Player of(final String name, final Money betAmount) {
        return new Player(name, betAmount);
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount.getAmount();
    }

    public BlackjackResult getBlackjackResult(Dealer dealer) {
        return BlackjackResult.getPlayerResult(dealer, this);
    }

    public void win(Dealer dealer) {
        increaseAmount(betAmount.getAmount());
        dealer.decreaseAmount(betAmount.getAmount());
    }

    public void lose(Dealer dealer) {
        decreaseAmount(betAmount.getAmount());
        dealer.increaseAmount(betAmount.getAmount());
    }

    @Override
    public boolean canHit() {
        return getScore() < Card.BLACKJACK_SCORE;
    }
}

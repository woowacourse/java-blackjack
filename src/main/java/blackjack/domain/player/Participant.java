package blackjack.domain.player;

import blackjack.domain.betting.Money;
import blackjack.domain.result.Result;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;
    private final Money betting;

    public Participant(final String name, final AcceptStrategy acceptStrategy) {
        super(name);
        this.acceptStrategy = acceptStrategy;
        this.betting = new Money(0);
    }

    private boolean isUnderMaxScore() {
        return cards.calculateDefaultScore() <= Result.MAX_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    public void increaseBetting() {
        betting.win();
    }

    public void increaseBlackjackBetting() {
        betting.winByBlackjack();
    }

    public void decreaseBetting() {
        betting.lose();
    }

    public Money betting() {
        return betting;
    }

    @Override
    public boolean acceptableCard() {
        return isUnderMaxScore() && acceptCard();
    }

    @Override
    public boolean isParticipant() {
        return true;
    }
}

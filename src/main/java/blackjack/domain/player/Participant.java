package blackjack.domain.player;

import blackjack.domain.betting.Money;
import blackjack.domain.result.Result;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;
    private final Money money;

    public Participant(final String name, final AcceptStrategy acceptStrategy, Money money) {
        super(name);
        this.acceptStrategy = acceptStrategy;
        this.money = money;
    }

    private boolean isUnderMaxScore() {
        return cards.calculateDefaultScore() <= Result.MAX_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    public void increaseBetting() {
        money.win();
    }

    public void increaseBlackjackBetting() {
        money.winByBlackjack();
    }

    public void decreaseBetting() {
        money.lose();
    }

    public Money betting() {
        return money;
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

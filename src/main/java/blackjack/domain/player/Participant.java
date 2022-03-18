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
        return cards.calculateEndScore() <= Result.BLACKJACK_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    public void increaseMoney() {
        money.win();
    }

    public void increaseBlackjackMoney() {
        money.winByBlackjack();
    }

    public void decreaseMoney() {
        money.lose();
    }

    public Money money() {
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

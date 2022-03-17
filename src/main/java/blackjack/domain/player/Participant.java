package blackjack.domain.player;

import blackjack.domain.betting.Money;
import blackjack.domain.result.Judge;

import java.util.Objects;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;
    private final Money betting;

    public Participant(final String name, final AcceptStrategy acceptStrategy) {
        super(name);
        this.acceptStrategy = acceptStrategy;
        this.betting = new Money(0);
    }

    private boolean isUnderMaxScore() {
        return cards.calculateDefaultScore() <= Judge.MAX_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    public void increaseBetting(int amount) {
        betting.increase(new Money(amount));
    }

    public void decreaseBetting(int amount) {
        betting.decrease(new Money(amount));
    }

    public Money betting() {
        return betting;
    }

    @Override
    public boolean acceptableCard() {
        return isUnderMaxScore() && acceptCard();
    }
}

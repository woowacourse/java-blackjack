package blackjack.domain.player;

import blackjack.domain.result.Judge;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;

    public Participant(final String name, final AcceptStrategy acceptStrategy) {
        super(name);
        this.acceptStrategy = acceptStrategy;
    }

    @Override
    public boolean acceptableCard() {
        return isUnderMaxScore() && acceptCard();
    }

    private boolean isUnderMaxScore() {
        return cards.calculateDefaultScore() <= Judge.MAX_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }
}

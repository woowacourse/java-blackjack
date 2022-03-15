package blackjack.domain.player;

import blackjack.domain.result.Judge;

public class Participant extends Player {

    private final AcceptStrategy inputStrategy;
    public Participant(final String name, final AcceptStrategy inputStrategy) {
        super(name);
        this.inputStrategy = inputStrategy;
    }

    @Override
    public boolean acceptableCard() {
        return isUnderMaxScore() && acceptCard();
    }

    private boolean isUnderMaxScore() {
        return cards.calculateScore() <= Judge.MAX_SCORE;
    }

    private boolean acceptCard() {
        return inputStrategy.accept(this.name);
    }
}

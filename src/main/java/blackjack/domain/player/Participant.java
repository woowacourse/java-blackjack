package blackjack.domain.player;

import blackjack.domain.result.Judge;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean acceptableCard() {
        return cards.calculateScore() <= Judge.MAX_SCORE;
    }
}

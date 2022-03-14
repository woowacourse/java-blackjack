package blackjack.domain.player;

import static blackjack.domain.Rule.WINNING_SCORE;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canTakeCard() {
        return getTotalScore() <= WINNING_SCORE.getValue();
    }
}

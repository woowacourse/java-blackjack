package blackjack.domain.player;

import blackjack.domain.result.Score;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canAddCard() {
        Score score = new Score(this);
        return score.CanAddPlayerCard();
    }
}

package blackjack.domain.player;

import blackjack.domain.result.Score;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canAddCard() {
        return new Score(this).CanAddPlayerCard();
    }
}

package blackjack.domain.player;

import blackjack.domain.card.Cards;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= Cards.BLACK_JACK_TARGET_SCORE;
    }
}

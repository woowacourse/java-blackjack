package domain.gamer;

import domain.card.PlayingCards;

public class Dealer extends Gamer {
    private static final int ADD_THRESHOLD = 17;
    private static final String NAME = "딜러";

    public Dealer(PlayingCards playingCards) {
        super(playingCards, NAME);
    }

    public boolean canGetExtraCard() {
        return playingCards.calculateScore() < ADD_THRESHOLD;
    }
}

package domain.gamer;

import domain.card.PlayingCards;

public class Dealer extends Gamer {
    private static final int ADD_THRESHOLD = 17;

    Dealer(PlayingCards playingCards) {
        super(playingCards);
    }

    public boolean canGetExtraCard() {
        return playingCards.calculateScore() < ADD_THRESHOLD;
    }
}

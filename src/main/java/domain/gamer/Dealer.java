package domain.gamer;

import domain.card.PlayingCards;

public class Dealer extends Gamer {
    public static final int HIT_THRESHOLD = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer(PlayingCards playingCards) {
        super(playingCards, DEALER_NAME);
    }

    public boolean isHittable() {
        return calculateScore().isLessThen(HIT_THRESHOLD);
    }
}

package domain.player;

import domain.card.Card;
import domain.player.hand.Hand;
import domain.player.hand.Score;

import java.util.List;

public final class Dealer {

    private static final int FIRST = 0;
    private static final int STAY_SCORE = 17;

    private final Hand hand;

    private Dealer(final Hand hand) {
        this.hand = hand;
    }

    public static Dealer create() {
        return new Dealer(Hand.create());
    }

    public void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public boolean canHit() {
        return score().isUnderThan(STAY_SCORE);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public Card showCard() {
        return getHand().get(FIRST);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Score score() {
        return hand.getScore();
    }

    public int getScore() {
        return score().getScore();
    }
}

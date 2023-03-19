package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.player.hand.Hand;
import domain.player.hand.Score;
import util.Notice;

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

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean canHit() {
        return getScore().isUnderThan(STAY_SCORE);
    }

    public void playDealerTurn(final Deck deck, final Notice<Boolean> notice) {
        while (canHit()) {
            notice.print(canHit());
            takeCard(deck.dealCard());
        }
        notice.print(canHit());
    }

    public Card showCard() {
        return getCards().get(FIRST);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.getScore();
    }

}

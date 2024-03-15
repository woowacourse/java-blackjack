package blackjack.domain.gamer;

import blackjack.domain.supplies.Card;
import blackjack.domain.supplies.Chip;
import blackjack.domain.supplies.Hand;

import java.util.List;

public class Gamer {
    private static final int BLACKJACK_CARDS_COUNT = 2;

    private final Hand hand;
    private final Chip chip;

    public Gamer(Hand hand, Chip chip) {
        this.hand = hand;
        this.chip = chip;
    }

    public void draw(List<Card> cards) {
        hand.add(cards);
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBlackjack() {
        return hand.getNumberOfCards() == BLACKJACK_CARDS_COUNT
                && hand.isLimitScore();
    }

    public boolean isBust() {
        return hand.isOverLimitScore();
    }

    public boolean isMaxScore() {
        return hand.isLimitScore();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public Card getCardAt(int index) {
        return hand.getMyCardAt(index);
    }

    public List<Card> getCards() {
        return hand.getMyCards();
    }
}

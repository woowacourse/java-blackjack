package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;

public class Gamer {
    private static final int BLACKJACK_CARDS_COUNT = 2;

    protected final Hand hand;

    public Gamer(Hand hand) {
        this.hand = hand;
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

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getMyCards();
    }
}

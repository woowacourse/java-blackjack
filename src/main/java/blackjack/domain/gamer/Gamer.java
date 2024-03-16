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
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isMaxScore() {
        return hand.isMaxScore();
    }

    public int getScore() {
        return hand.score().toInt();
    }

    public Card getCardAt(int index) {
        return hand.getMyCardAt(index);
    }

    public List<Card> getCards() {
        return hand.getMyCards();
    }

    public int getChip() {
        return chip.getChip();
    }
}

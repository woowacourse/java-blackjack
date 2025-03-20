package participant;

import card.Card;
import card.Cards;
import java.util.List;

public class Dealer {

    private final Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public void prepareGame(final Card card1, final Card card2) {
        cards.add(card1);
        cards.add(card2);
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public Card firstRoundCard() {
        return cards.get(1);
    }

    public int score() {
        return cards.calculateScore();
    }

    public boolean isBlackjack() {
        return cards.calculateScore() == 21 && cards.size() == 2;
    }

    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    public boolean canReceiveCard() {
        return score() <= 16;
    }

    public List<Card> cards() {
        return cards.getCards();
    }
}

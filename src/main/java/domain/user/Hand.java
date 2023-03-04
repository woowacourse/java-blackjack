package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import domain.game.GameRule;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getNumber().getValue();
        }

        if (sum > GameRule.BLACKJACK.getNumber() && containsAce()) {
            sum -= GameRule.BLACKJACK.getNumber() - Denomination.ACE.getValue();
        }
        return sum;
    }

    public boolean isOverBlackjack() {
        return sumCardNumbers() > GameRule.BLACKJACK.getNumber();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == Denomination.ACE);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}

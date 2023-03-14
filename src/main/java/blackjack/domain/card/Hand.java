package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int sum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getNumber().getValue();
        }

        return sum;
    }

    public int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getCount() {
        return cards.size();
    }

}

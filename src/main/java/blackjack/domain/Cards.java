package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

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
                .filter(card -> card.getNumber().equals(Number.ACE))
                .count();
    }

    public int getCount() {
        return cards.size();
    }

}

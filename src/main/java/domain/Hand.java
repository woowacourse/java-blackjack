package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .map(CardNumber::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }
}

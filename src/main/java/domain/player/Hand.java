package domain.player;

import domain.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();


    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(card -> card.cardNumber().getPoint())
                .sum();
    }
}

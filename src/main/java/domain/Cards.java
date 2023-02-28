package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}

package domain.player;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards = new ArrayList<>();
    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardSize() {
        return cards.size();
    }

    public boolean isBust() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum() > 21;
    }

    public int getTotalValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }
}

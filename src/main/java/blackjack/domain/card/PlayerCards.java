package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isContains(CardNumber number) {
        return cards.stream().anyMatch(card -> card.getNumber() == number);
    }
}

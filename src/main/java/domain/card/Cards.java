package domain.card;

import java.util.ArrayList;
import java.util.List;

public record Cards(List<Card> cards) {

    public Cards() {
        this(List.of());
    }

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public Cards addCard(Card card) {
        List<Card> cards = new ArrayList<>(this.cards);
        cards.add(card);
        return new Cards(cards);
    }
}

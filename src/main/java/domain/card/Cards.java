package domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> init) {
        cards = init;
    }

    public int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

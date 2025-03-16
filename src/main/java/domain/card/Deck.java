package domain.card;

import java.util.List;
import java.util.Objects;

public class Deck {

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck from(List<Card> cards) {
        return new Deck(cards);
    }

    public Card extractCard() {
        return cards.removeLast();
    }

    public List<Card> getCards() {
        return cards.stream()
                .map(card -> new Card(card.getSymbol(), card.getRank()))
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck cards1 = (Deck) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

}

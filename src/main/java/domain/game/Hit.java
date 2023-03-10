package domain.game;

import domain.card.Card;

import java.util.List;

public class Hit {

    final List<Card> cards;

    public Hit(Card card1, Card card2) {
        cards = List.of(card1, card2);
    }

    public Bust draw(Card of) {
        return new Bust();
    }

    public List<Card> cards() {
        return cards;
    }
}

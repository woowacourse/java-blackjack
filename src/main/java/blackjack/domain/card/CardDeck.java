package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(CardsGenerator generator) {
        cards = generator.generate();
    }

    public Card draw() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

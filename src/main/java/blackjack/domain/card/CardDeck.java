package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private List<Card> cards;
    private final CardsGenerator generator;

    public CardDeck(CardsGenerator generator) {
        this.generator = generator;
        initCards();
    }

    private void initCards() {
        cards = generator.generate();
    }

    public Card draw() {
        if (isEmpty()) {
            initCards();
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }

    private boolean isEmpty() {
        return cards.isEmpty();
    }
}

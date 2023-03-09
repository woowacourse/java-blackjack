package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        generateDeck();
        Collections.shuffle(cards);
    }

    private void generateDeck() {
        for (CardShape cardShape : CardShape.values()) {
            generateDeckForEachShape(cardShape);
        }
    }

    private void generateDeckForEachShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public Card getCard() {
        return cards.remove(0);
    }
}

package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final NumberGenerator numberGenerator;

    public Deck(NumberGenerator numberGenerator) {
        generateDeck();
        this.numberGenerator = numberGenerator;
    }

    private void generateDeck() {
        for (CardShape cardShape : CardShape.values()) {
            generateTrumpForEachShape(cardShape);
        }
    }

    private void generateTrumpForEachShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public Card getCard() {
        return cards.remove(numberGenerator.generate(cards.size()));
    }
}

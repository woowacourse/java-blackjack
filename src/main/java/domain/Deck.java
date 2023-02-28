package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final NumberGenerator numberGenerator;
    private final List<Card> cards;

    public Deck(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.cards = new ArrayList<>();
        initializeCards();
    }

    private void initializeCards() {
        for (CardType cardType : CardType.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardType, cardNumber));
            }
        }
    }

    public Card draw() {
        return cards.get(numberGenerator.generate(cards.size()));
    }
}

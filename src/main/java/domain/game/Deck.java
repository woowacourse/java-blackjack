package domain.game;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.strategy.NumberGenerator;

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
        for (Suit suit : Suit.values()) {
            mapCardNumbers(suit);
        }
    }

    private void mapCardNumbers(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    public Card serve() {
        return cards.remove(numberGenerator.generate(cards.size()));
    }
}

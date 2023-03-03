package domain.game;

import domain.strategy.NumberGenerator;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;

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
            mapCardNumbers(cardType);
        }
    }

    private void mapCardNumbers(CardType cardType) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardType, cardNumber));
        }
    }

    public Card serve() {
        return cards.get(numberGenerator.generate(cards.size()));
    }
}

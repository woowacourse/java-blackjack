package domain.game;

import domain.strategy.NumberGenerator;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static final List<Card> cards;

    private final NumberGenerator numberGenerator;

    static {
        cards = makeCards();
    }

    public Deck(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    private static List<Card> makeCards() {
        List<Card> cardsOfAllCardType = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            cardsOfAllCardType.addAll(makeCardsOfOneCardType(cardType));
        }
        return cardsOfAllCardType;
    }

    private static List<Card> makeCardsOfOneCardType(CardType cardType) {
        List<Card> cardsOfOneCardType = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            cardsOfOneCardType.add(new Card(cardType, cardNumber));
        }
        return cardsOfOneCardType;
    }

    public Card serve() {
        return cards.get(numberGenerator.generate(cards.size()));
    }
}

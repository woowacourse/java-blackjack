package utils.generator;

import domain.Card;
import domain.CardRank;
import domain.CardShape;
import domain.Deck;

import java.util.ArrayList;
import java.util.List;

public final class CardGenerator {
    public static Deck generate() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            cards.addAll(createCardsFromRank(cardShape));
        }
        return new Deck(cards);
    }

    private static List<Card> createCardsFromRank(CardShape cardShape) {
        List<Card> cards = new ArrayList<>();
        for (CardRank cardRank : CardRank.values()) {
            cards.add(new Card(cardShape, cardRank));
        }
        return cards;
    }
}

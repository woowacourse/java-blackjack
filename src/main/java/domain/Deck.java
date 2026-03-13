package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private final List<Card> totalDeck;

    private Deck(List<Card> cards) {
        this.totalDeck = cards;
    }

    public static Deck createTotalDeckAndShuffle(CardShuffleStrategy strategy) {
        List<Card> cards = createAllCards();
        shuffleCards(cards, strategy);
        return new Deck(cards);
    }

    private static List<Card> createAllCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            addCardsByShape(cards, cardShape);
        }
        return cards;
    }

    private static void addCardsByShape(List<Card> cards, CardShape cardShape) {
        for (CardContents cardContents : CardContents.values()) {
            cards.add(new Card(cardShape, cardContents));
        }
    }

    private static void shuffleCards(List<Card> cards, CardShuffleStrategy strategy) {
        strategy.shuffle(cards);
    }

    public Card drawCard() {
        if (totalDeck.isEmpty()) {
            throw new NoSuchElementException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }
        return totalDeck.removeFirst();
    }

    public boolean isDrawable() {
        return !totalDeck.isEmpty();
    }
}

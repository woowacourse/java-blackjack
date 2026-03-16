package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(CardShuffler cardShuffler) {
        List<Card> cards = generateAllCards();
        cardShuffler.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> generateAllCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            addCardsByShape(cards, shape);
        }
        return cards;
    }

    private static void addCardsByShape(List<Card> cards, CardShape shape) {
        for (CardNumber number : CardNumber.values()) {
            cards.add(Card.of(number, shape));
        }
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 남은 카드가 없습니다.");
        }
        return cards.removeFirst();
    }
}

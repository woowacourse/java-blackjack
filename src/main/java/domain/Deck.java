package domain;

import java.util.LinkedList;

public class Deck {

    private final LinkedList<Card> cards;

    private Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public static Deck createFullDeck() {
        final LinkedList<Card> cards = new LinkedList<>();
        fillAllCards(cards);
        return new Deck(cards);
    }

    private static void fillAllCards(LinkedList<Card> cards) {
        for (Suit suit : Suit.values()) {
            fillAllCardsOfSuit(cards, suit);
        }
    }

    private static void fillAllCardsOfSuit(LinkedList<Card> cards, Suit suit) {
        for (Number number : Number.values()) {
            cards.add(Card.of(suit, number));
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
        return cards.pollLast();
    }

    public void shuffle(ShuffleStrategy shuffleStrategy) {
        shuffleStrategy.shuffle(cards);
    }
}

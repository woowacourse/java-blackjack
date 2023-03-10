package domain.card;

import java.util.LinkedList;

public class Deck {

    private final LinkedList<Card> cards;

    private Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        final LinkedList<Card> cards = new LinkedList<>();
        addAllCards(cards);
        return new Deck(cards);
    }

    private static void addAllCards(LinkedList<Card> cards) {
        for (Suit suit : Suit.values()) {
            addAllNumbersInSuit(cards, suit);
        }
    }

    private static void addAllNumbersInSuit(LinkedList<Card> cards, Suit suit) {
        for (Number number : Number.values()) {
            cards.add(new Card(suit, number));
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

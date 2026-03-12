package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.strategy.ShuffleStrategy;

import java.util.ArrayList;
import java.util.List;


public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(ShuffleStrategy shuffleStrategy) {
        List<Card> orderedCards = createOrderedDeck();
        List<Card> shuffledCards = shuffleStrategy.shuffle(orderedCards);
        return new Deck(shuffledCards);
    }

    private static List<Card> createOrderedDeck() {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        return cards;
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public List<Card> drawInitialCards(int initialCardsCount) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < initialCardsCount; i++) {
            cards.add(draw());
        }

        return cards;
    }
}

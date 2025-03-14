package model.card;

import java.util.*;

public final class CardDeck {

    private final Deque<Card> deck = shuffledInitDeck();

    private static Deque<Card> shuffledInitDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = createCard(suit, rank);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        return new ArrayDeque<>(deck);
    }

    private static Card createCard(Suit suit, Rank rank) {
        if (rank.isMultiScores()) {
            return new MultiScoreCard(suit, rank);
        }
        return new SingleScoreCard(suit, rank);
    }

    public List<Card> pickCard(int amount) {
        final List<Card> findCards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            findCards.add(deck.removeFirst());
        }
        return findCards;
    }

    public Deque<Card> getDeck() {
        return deck;
    }
}

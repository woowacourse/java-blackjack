package domain.deck;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    public static final int INITIAL_CARDS_COUNT = 2;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(CardShuffleStrategy cardShuffleStrategy) {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(
                        rank -> Arrays.stream(Suit.values())
                                .map(suit -> new Card(rank, suit))
                )
                .collect(Collectors.toCollection(ArrayList::new));

        cardShuffleStrategy.shuffle(cards);

        return new Deck(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public List<Card> drawInitialCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cards.add(draw());
        }

        return cards;
    }
}

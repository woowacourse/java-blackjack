package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class ShuffledDeckCardGenerator implements DeckCardGenerator {
    private static final ShuffledDeckCardGenerator INSTANCE = new ShuffledDeckCardGenerator();

    private ShuffledDeckCardGenerator() {
    }

    public static ShuffledDeckCardGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Deque<Card> generate() {
        return new ArrayDeque<>(createShuffledCards());
    }

    private List<Card> createShuffledCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(createCardsOfSuit(suit));
        }

        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> createCardsOfSuit(final Suit suit) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            cards.add(Card.of(suit, rank));
        }

        return cards;
    }
}

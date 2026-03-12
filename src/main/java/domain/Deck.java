package domain;

import domain.shuffle.CardShuffleStrategy;

import static java.util.Arrays.*;

import java.util.stream.Stream;

public class Deck {
    private final Cards cards;

    private Deck(CardShuffleStrategy shuffleStrategy) {
        this.cards = generateCards();
        shuffleStrategy.shuffle(cards);
    }

    public static Deck of(CardShuffleStrategy shuffleStrategy) {
        return new Deck(shuffleStrategy);
    }

    private Cards generateCards() {
        return Cards.of(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public Cards drawInitialHand() {
        return Cards.of(Stream.generate(this::draw)
                .limit(Policy.FIRST_DRAW_SIZE)
                .toList());
    }

    public Card draw() {
        return cards.removeFirst();
    }
}

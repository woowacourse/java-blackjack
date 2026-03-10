package domain;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private final List<Card> cards;

    private Deck(CardShuffleStrategy shuffleStrategy) {
        this.cards = generateCards();
        shuffleStrategy.shuffle(cards);
    }

    public static Deck of(CardShuffleStrategy shuffleStrategy) {
        return new Deck(shuffleStrategy);
    }

    private List<Card> generateCards() {
        return new ArrayList<>(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public List<Card> drawInitialHand() {
        return new ArrayList<>(Stream.generate(this::draw)
                .limit(Policy.FIRST_DRAW_SIZE)
                .toList());
    }

    public Card draw() {
        return cards.removeFirst();
    }
}

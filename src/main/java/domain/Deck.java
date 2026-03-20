package domain;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private static final int INITIAL_HAND_SIZE = 2;

    private final List<Card> cards;

    private Deck(CardShuffler cardShuffler) {
        this.cards = cardShuffler.shuffle(generateCards());
    }

    public static Deck of(CardShuffler cardShuffler) {
        return new Deck(cardShuffler);
    }

    private List<Card> generateCards() {
        return new ArrayList<>(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public List<Card> drawInitialHand() {
        return new ArrayList<>(Stream.generate(this::draw)
                .limit(INITIAL_HAND_SIZE)
                .toList());
    }

    public Card draw() {
        return cards.removeFirst();
    }

}

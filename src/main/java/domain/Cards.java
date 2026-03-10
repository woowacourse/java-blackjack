package domain;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    private Cards(CardShuffleStrategy shuffleStrategy) {
        this.cards = generateCards();
        shuffleStrategy.shuffle(cards);
    }

    public static Cards of(CardShuffleStrategy shuffleStrategy) {
        return new Cards(shuffleStrategy);
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

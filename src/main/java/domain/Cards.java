package domain;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cards {


    private final List<Card> cards;

    private Cards(CardShuffler cardShuffler) {
        this.cards = cardShuffler.shuffle(generateCards());
    }

    public static Cards of(CardShuffler cardShuffler) {
        return new Cards(cardShuffler);
    }

    private List<Card> generateCards() {
        return new ArrayList<>(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public List<Card> drawInitialHand() {
        return new ArrayList<>(Stream.generate(this::draw)
                .limit(BlackjackRule.INITIAL_HAND_SIZE)
                .toList());
    }

    public Card draw() {
        return cards.removeFirst();
    }

}

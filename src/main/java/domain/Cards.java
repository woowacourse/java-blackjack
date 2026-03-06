package domain;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private List<Card> cards;

    private Cards() {
        this.cards = generateCards();
    }

    public static Cards of() {
        return new Cards();
    }

    private List<Card> generateCards() {
        return new ArrayList<>(stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(rank, suit)))
                .toList());
    }

    public List<Card> drawInitialHand() {
        return Stream.generate(this::draw)
                .limit(2)
                .toList();
    }

    public Card draw() {
        return cards.removeFirst();
    }

}

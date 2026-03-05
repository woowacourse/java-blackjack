package domain;

import static java.util.Arrays.*;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = generateCards();
    }

    public static Cards of() {
        return new Cards();
    }

    private List<Card> generateCards() {
        return stream(Rank.values())
                .flatMap(rank -> stream(Suit.values())
                        .map(suit -> Card.of(suit, rank)))
                .toList();
    }

}

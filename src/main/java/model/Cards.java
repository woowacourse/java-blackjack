package model;

import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards withScore(final int score, final List<Name> names) {
        List<Card> cards = names.stream()
                .map(name -> Card.createDefault(name, score))
                .collect(Collectors.toList());
        return new Cards(cards);
    }
}

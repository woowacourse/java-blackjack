package blackjack;

import java.util.List;

public class SoftHandCards {

    private final List<Card> cards;

    public SoftHandCards(Card... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        int score = cards.stream()
            .mapToInt(Card::softRank)
            .sum();
        return new Score(score);
    }

}

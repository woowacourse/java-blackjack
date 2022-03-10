package blackjack.cards;

import blackjack.Card;
import blackjack.Score;
import java.util.List;
import java.util.stream.Stream;

public class HardHandCards implements Cards {

    private final List<Card> cards;

    public HardHandCards(Card... cards) {
        this.cards = List.of(cards);
    }

    @Override
    public Score score() {
        return new Score(hardHandScore());
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }

    private int hardHandScore() {
        return cards.stream()
                .mapToInt(Card::hardRank)
                .sum();
    }
}

package blackjack.cards;

import blackjack.Card;
import blackjack.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class HardHandCards implements Cards {

    private final List<Card> cards;

    HardHandCards(Card... cards) {
        this.cards = new ArrayList<>(List.of(cards));
    }

    @Override
    public Score score() {
        return new Score(hardHandScore());
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }

    @Override
    public void take(Card card) {
        cards.add(card);
    }

    private int hardHandScore() {
        return cards.stream()
                .mapToInt(Card::hardRank)
                .sum();
    }
}

package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Hit extends Running {
    private final Cards cards;

    public Hit(final Card... cards) {
        this(Arrays.stream(cards).collect(toList()));
    }

    public Hit(final List<Card> cards) {
        this(new Cards(cards));
    }

    public Hit(final Cards cards) {
        this.cards = cards;
    }

    public State stay() {
        return new Stay();
    }

    @Override
    public State draw(final Card card) {
        this.cards.combine(new Cards(Collections.singletonList(card)));
        if (this.cards.calculateScore().isBust()) {
            return new Bust();
        }
        return new Hit(card);
    }
}

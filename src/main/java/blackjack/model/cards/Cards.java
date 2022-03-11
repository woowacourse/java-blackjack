package blackjack.model.cards;

import blackjack.model.Card;
import java.util.stream.Stream;

public interface Cards extends Iterable<Card> {

    void take(Card card);

    Stream<Card> stream();

    static OwnCards of(Card card) {
        return new OwnCards(card);
    }

    static OwnCards of(Card card1, Card card2, Card... cards) {
        return new OwnCards(card1, card2, cards);
    }

    static ImmutableCards toUnmodifiable(Cards cards) {
        return new ImmutableCards(cards);
    }

    static BestScoreCards bestScoreCards(Cards cards) {
        return new BestScoreCards(cards);
    }

    static MaxScoreCards maxScoreCards(Cards ownCards) {
        return new MaxScoreCards(ownCards);
    }
}

package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.stream.Stream;

public interface Cards extends Iterable<Card> {

    Cards openedCards(int count);

    void take(Card card);

    Stream<Card> stream();

    static HandCards of(Card card1, Card card2, Card... cards) {
        return new HandCards(card1, card2, cards);
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

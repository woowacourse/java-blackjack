package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Collection;

public interface Cards {

    Cards openedCards(int count);

    void take(Card card);

    Collection<Card> values();

    static HandCards of(Card card1, Card card2, Card... cards) {
        return new HandCards(card1, card2, cards);
    }

    static HandCards copyOf(Cards cards) {
        return new HandCards(cards);
    }

    static BestScoreCards bestScoreCards(Cards cards) {
        return new BestScoreCards(cards);
    }

    static MaxScoreCards maxScoreCards(Cards ownCards) {
        return new MaxScoreCards(ownCards);
    }
}

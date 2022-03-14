package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Collection;

public interface Cards {

    Cards openedCards(int count);

    Collection<Card> values();

    static HandCards of(Card card1, Card card2, Card... cards) {
        return new HandCards(card1, card2, cards);
    }

    static BestScoreCards bestScoreCards(TakableCards cards) {
        return new BestScoreCards(cards);
    }

    static MaxScoreCards maxScoreCards(TakableCards ownCards) {
        return new MaxScoreCards(ownCards);
    }

}

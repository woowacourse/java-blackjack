package blackjack.domain.hand;

import blackjack.domain.card.Card;

public interface CardHand {

    CardHand hit(Card card);

    CardHand stay();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}

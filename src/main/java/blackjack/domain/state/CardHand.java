package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public interface CardHand {

    CardHand hit(Card card);

    CardHand stay();

    CardBundle getCardBundle();

    double getBettingYieldVersus(CardHand targetState);

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}

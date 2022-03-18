package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public interface CardHand {

    CardHand hit(Card card);

    CardHand stay();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();

    double getBettingYieldVersus(CardHand targetState);

    CardBundle getCardBundle();
}

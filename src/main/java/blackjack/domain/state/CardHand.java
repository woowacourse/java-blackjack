package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.DuelResult;

public interface CardHand {

    CardHand hit(Card card);

    CardHand stay();

    CardBundle getCardBundle();

    DuelResult getDuelResultOf(CardHand targetState);

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}

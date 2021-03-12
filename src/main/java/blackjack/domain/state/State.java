package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.player.BetAmount;

public interface State {
    State stay();

    State draw(Card card);

    boolean isFinished();

    Hand cards();

    double profit(State state, BetAmount amount);

    int score();

    boolean isBlackjack();

    boolean isBust();
}

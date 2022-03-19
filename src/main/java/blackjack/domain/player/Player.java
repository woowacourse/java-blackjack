package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import blackjack.domain.state.State;

public interface Player {

    void hit(Card card);

    void stay();

    boolean isBust();

    boolean isBlackjack();

    Name getName();

    PlayerCards getPlayerCards();

    State getState();

    int getScore();

    boolean isDealer();

    boolean canHit();
}

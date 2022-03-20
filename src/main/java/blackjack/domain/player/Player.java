package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;
import blackjack.domain.state.State;

public interface Player {

    boolean isDealer();

    boolean isHit();

    boolean isBlackjack();

    boolean isBust();

    boolean isDraw(PlayingCards playingCards);

    boolean isLose(PlayingCards playingCards);

    void changeState(State state);

    String getName();

    State getState();
}

package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface BlackjackGameState {

    BlackjackGameState hit(final Card card);

    BlackjackGameState stay();

    boolean isFinished();

    double earningRate(final BlackjackGameState blackjackGameState);
}

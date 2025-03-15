package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.state.finished.FinishedState;
import blackjack.model.state.running.RunningState;
import java.util.List;

public sealed interface State
        permits RunningState, FinishedState {

    State receiveCard(Card card);

    State stand();

    boolean isFinished();

    List<Card> getHandCards();

    int getTotal();
}

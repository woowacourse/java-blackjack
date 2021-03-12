package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Score;

import java.util.List;

public interface State {
    boolean isFinished();

    State receiveCard(final Card card);

    State stay();

    Hand hand();

    List<Card> toHandList();

    Score totalScore();

    double profit(final double money);
}

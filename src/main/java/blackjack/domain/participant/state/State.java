package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Score;
import blackjack.domain.participant.ResultStatus;

public interface State {

    State draw(Card card);

    State stay();

    Score calculateScore();

    boolean isFinished();

    double calculateEarningRate(ResultStatus resultStatus);

    CardHand getCardHand();
}

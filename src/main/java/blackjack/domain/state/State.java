package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public interface State {

    ParticipantCards participantCards();

    State draw(Card card);

    State stay();

    boolean isFinished();

    boolean isBust();

    boolean isBlackjack();

    int getScore();

    double profit(double money, State dealerState);

}

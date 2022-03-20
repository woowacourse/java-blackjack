package blackjack.domain.participant.state;

import java.util.List;

import blackjack.domain.card.Card;

public interface State {

    State drawCard(final Card card);

    State stay();

    boolean isPossibleToDrawCard();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();

    List<Card> getCards();

    int getScore();

}

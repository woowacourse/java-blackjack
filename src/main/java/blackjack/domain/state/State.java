package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public interface State {

    State draw(Card card);

    State stay();

    BlackjackMatch match(State state);

    double profitRate(BlackjackMatch blackjackMatch);

    boolean isFinished();

    boolean isRunning();

    boolean isBust();

    Cards getCards();
}

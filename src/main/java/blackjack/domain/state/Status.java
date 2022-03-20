package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public interface Status {

    Status draw(Card card);

    Status stay();

    BlackjackMatch showMatch(Status status);

    double profitRate(BlackjackMatch blackjackMatch);

    boolean isFinished();

    boolean isRunning();

    boolean isBust();

    Cards getCards();
}

package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.user.Dealer;

import java.util.List;

public interface State {
    State draw(Card card);

    List<Card> getCards();

    Score score();

    State stay();

    boolean isFinished();

    double earningRate(Dealer dealer);

    boolean isBlackjack();

    boolean isBust();
}

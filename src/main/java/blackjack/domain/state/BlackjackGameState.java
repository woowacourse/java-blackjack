package blackjack.domain.state;

import blackjack.domain.card.Card;
import java.util.List;

public interface BlackjackGameState {

    BlackjackGameState hit(final Card card);

    BlackjackGameState stay();

    boolean isFinished();

    int profit(final int betMoney, final BlackjackGameState dealerGameState);

    boolean isBust();

    boolean isBlackjack();

    int score();

    List<Card> cards();
}

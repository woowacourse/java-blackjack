package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.state.State;

import java.util.List;

public interface Participant {
    void receiveCard(Card card);

    List<Card> showInitCards();

    List<Card> showCards();

    boolean isReceiveCard();

    int sumTotalScore();

    String getName();

    boolean isDealer();

    GameResult calculateResult(int enemyScore);

    void betting(int money);

    State getStatus();

    void stay();
}


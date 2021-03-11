package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.state.State;

import java.util.List;

public interface Participant {
    boolean receiveCard(Card card);

    List<Card> showInitCards();

    List<Card> showCards();

    boolean isReceiveCard();

    int sumTotalScore(ScoreRule scoreRule);

    String getName();

    boolean isDealer();

    State getStatus();

    void betting(int money);

    void stay();
}


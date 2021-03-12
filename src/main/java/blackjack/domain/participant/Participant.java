package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.List;

public interface Participant {

    default int sumTotalScore(ScoreRule scoreRule) {
        return getStatus().getCards().sumTotalScore(scoreRule);
    }

    boolean handOutCard(Card card);

    List<Card> showInitCards();

    List<Card> showCards();

    boolean isReceiveCard();

    String getName();

    boolean isDealer();

    State getStatus();

    void betting(int money);

    void stay();
}


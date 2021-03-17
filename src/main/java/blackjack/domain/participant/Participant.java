package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;

import java.util.List;

public interface Participant {

    int sumTotalScore(ScoreRule scoreRule);

    List<Card> showCards();

    void receiveCard(Card card);

    void changeState();

    void stay();

    boolean isEnd();

    boolean isBust();

    boolean isBlackJack();
}


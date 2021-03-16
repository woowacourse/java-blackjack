package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.ScoreRule;

public interface State {
    default void draw(Card card) {
        if (!isEndState()) {
            getCards().receiveCard(card);
        }
    }

    boolean isEndState();

    boolean isBust();

    boolean isBlackJack();

    double calculateEarningRate(State enemyState);

    int sumTotalScore(ScoreRule scoreRule);

    State changeState();

    State stay();

    Cards getCards();
}
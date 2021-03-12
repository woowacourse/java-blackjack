package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.ScoreRule;

public interface State {
    boolean isEndState();
    boolean isBust();
    boolean isBlackJack();
    double calculateEarningRate(State enemyState);
    int sumTotalScore(ScoreRule scoreRule);
    void draw(Card card);
    State changeState();
    State stay();
    Cards getCards();
}
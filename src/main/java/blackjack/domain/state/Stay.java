package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.rule.ScoreRule;

import java.util.List;

public class Stay implements State {
    private Cards cards;

    public Stay(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double calculateEarningRate(State enemyState) {
        int enemyScore = enemyState.sumTotalScore(new BlackJackScoreRule());
        if (enemyState.isBust() || sumBlackJackTotalScore() > enemyScore) {
            return 1;
        }

        if (enemyState.isBlackJack() || sumBlackJackTotalScore() < enemyScore) {
            return -1;
        }

        return 0;
    }

    private int sumBlackJackTotalScore() {
        return sumTotalScore(new BlackJackScoreRule());
    }

    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return scoreRule.sumTotalScore(cards.toCardList());
    }

    @Override
    public State changeState() {
        return this;
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}

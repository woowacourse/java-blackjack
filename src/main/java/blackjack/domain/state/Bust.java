package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.ScoreRule;

import java.util.List;

public class Bust implements State {
    private Cards cards;

    public Bust(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double calculateEarningRate(State enemyState) {
        return -1;
    }

    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return cards.sumTotalScore(scoreRule);
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

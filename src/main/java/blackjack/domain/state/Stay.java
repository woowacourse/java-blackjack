package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.BlackJackScoreRule;

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
    public double profit(State enemyState) {
        int score = cards.getTotalScore(new BlackJackScoreRule());
        int enemyScore = cards.getTotalScore(new BlackJackScoreRule());
        if (enemyState.isBust() || score > enemyScore) {
            return 1;
        }

        if (score < enemyScore) {
            return -1;
        }

        return 0;
    }

    @Override
    public void draw(Card card) {
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

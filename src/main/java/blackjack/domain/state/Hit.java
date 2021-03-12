package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.rule.ScoreRule;

import java.util.List;

public class Hit implements State {
    private static final int INITIAL_STATE_NUMBER_OF_CARDS = 2;
    private static final int BLACK_JACK = 21;
    private Cards cards;

    public Hit(List<Card> initialCard) {
        cards = new Cards(initialCard);
    }

    @Override
    public boolean isEndState() {
        return false;
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
        return 0;
    }

    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return cards.sumTotalScore(scoreRule);
    }

    @Override
    public void draw(Card card) {
        cards.receiveCard(card);
    }

    @Override
    public State changeState() {
        int sum = cards.sumTotalScore(new BlackJackScoreRule());
        if (sum == BLACK_JACK && isInitialState()) {
            return new BlackJack(cards.toCardList());
        }

        if (sum > BLACK_JACK) {
            return new Bust(cards.toCardList());
        }

        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards.toCardList());
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    private boolean isInitialState() {
        return cards.size() == INITIAL_STATE_NUMBER_OF_CARDS;
    }
}

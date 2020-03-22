package blackjack.domain.calculator;

import blackjack.domain.card.CardBundle;

class DefaultScoreStrategy implements ScoreStrategy {

    @Override
    public boolean support(CardBundle cards) {
        return !cards.hasAce();
    }

    @Override
    public int calculate(CardBundle cards) {
        return cards.calculateScore();
    }

}

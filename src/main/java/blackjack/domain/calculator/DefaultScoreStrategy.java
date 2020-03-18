package blackjack.domain.calculator;

import blackjack.domain.card.Card;

import java.util.List;

class DefaultScoreStrategy implements ScoreStrategy {
    @Override
    public boolean support(List<Card> cards) {
        return true;
    }

    @Override
    public int calculate(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }
}

package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public class DefaultScoreStrategy implements ScoreStrategy {
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

package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public interface ScoreStrategy {
    static int sum(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    boolean support(List<Card> cards);

    int calculate(List<Card> cards);
}

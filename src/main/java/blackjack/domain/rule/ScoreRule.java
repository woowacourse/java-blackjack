package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.List;

public interface ScoreRule {
    int sumTotalScore(List<Card> cards);
}

package blackjack.domain;

import java.util.List;

public interface ScoreRule {
    int sumTotalScore(List<Card> cards);
}

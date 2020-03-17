package second.domain.score;

import second.domain.card.HandCards;
import second.domain.card.Score;

import java.util.List;

public interface ScoreCalculateStrategy {
    Score calculate(HandCards handCards);
}

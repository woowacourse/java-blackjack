package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Score;

final class MaxScoreCards extends ScoreCards{

    MaxScoreCards(Cards cards) {
        super(cards);
    }

    public Score score() {
        int score = stream()
            .mapToInt(Card::softRank)
            .sum();
        return new Score(score);
    }
}

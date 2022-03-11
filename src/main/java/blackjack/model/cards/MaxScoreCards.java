package blackjack.model.cards;

import blackjack.model.card.Card;

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

package blackjack.model.cards;

import blackjack.model.card.Card;

final class MaxScoreCards extends ScoreCards{

    MaxScoreCards(TakableCards cards) {
        super(cards);
    }

    @Override
    public Score score() {
        return new Score(softHandScore());
    }

    private int softHandScore() {
        return values().stream()
            .mapToInt(Card::softRank)
            .sum();
    }
}

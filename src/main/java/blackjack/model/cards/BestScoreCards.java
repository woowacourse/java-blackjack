package blackjack.model.cards;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;

final class BestScoreCards extends ScoreCards {

    BestScoreCards(TakableCards cards) {
        super(cards);
    }

    @Override
    public Score score() {
        if (hasAce() && !softHandScore().isBust()) {
            return softHandScore();
        }
        return hardHandScore();
    }

    private boolean hasAce() {
        return values().stream().anyMatch(Card::isAce);
    }

    private Score softHandScore() {
        return hardHandScore().plus(increaseScore());
    }

    private Score increaseScore() {
        return new Score(Rank.ACE.soft() - Rank.ACE.hard());
    }

    private Score hardHandScore() {
        int score = values().stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}

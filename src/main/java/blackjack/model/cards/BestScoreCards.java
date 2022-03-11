package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Rank;
import blackjack.model.Score;

final class BestScoreCards extends ScoreCards {

    BestScoreCards(Cards cards) {
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
        return stream().anyMatch(Card::isAce);
    }

    private Score softHandScore() {
        return hardHandScore().plus(increaseScore());
    }

    private Score increaseScore() {
        return new Score(Rank.ACE.soft() - Rank.ACE.hard());
    }

    private Score hardHandScore() {
        int score = stream()
            .mapToInt(Card::hardRank)
            .sum();
        return new Score(score);
    }
}

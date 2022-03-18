package blackjack.domain.machine.result;

import blackjack.domain.machine.Score;

public class JudgeFactory {

    public static MatchResults matchResult(Score playerScore, Score dealerScore) {
        if (playerScore.isBlackjack() && !dealerScore.isBlackjack()) {
            return new Blackjack();
        }
        if (playerScore.isBust() || (playerScore.getScore() < dealerScore.getScore()) && !dealerScore.isBust()) {
            return new Lose();
        }
        if (playerScore.getScore() == dealerScore.getScore()) {
            return new Draw();
        }
        return new Win();
    }
}

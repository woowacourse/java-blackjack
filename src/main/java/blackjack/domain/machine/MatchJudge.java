package blackjack.domain.machine;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum MatchJudge {

    BLACKJACK_WIN(MatchJudge::isBlackjack, 1.5),
    LOSE(MatchJudge::isLose, -1),
    DRAW(MatchJudge::isDraw, 0),
    WIN(MatchJudge::isWin, 1),
    ;

    private final BiPredicate<Score, Score> condition;
    private final double rate;

    MatchJudge(BiPredicate<Score, Score> condition,
               double rate) {
        this.condition = condition;
        this.rate = rate;
    }

    public static MatchJudge judgeMatch(Score playerScore, Score dealerScore) {

        return Arrays.stream(MatchJudge.values())
                .filter(findMatchResult(playerScore, dealerScore))
                .findFirst()
                .orElse(WIN);
    }

    public double calculateProfit(double money) {
        return money * rate;
    }

    private static Predicate<MatchJudge> findMatchResult(Score playerScore, Score dealerScore) {
        return profit -> profit.condition.test(playerScore, dealerScore);
    }

    private static boolean isBlackjack(Score playerScore, Score dealerScore) {
        return playerScore.isBlackjackWin(dealerScore);
    }

    private static boolean isLose(Score playerScore, Score dealerScore) {
        return playerScore.isLose(dealerScore);
    }

    private static boolean isDraw(Score playerScore, Score dealerScore) {
        return playerScore.isDraw(dealerScore);
    }

    private static boolean isWin(Score playerScore, Score dealerScore) {
        return playerScore.isWin(dealerScore);
    }
}

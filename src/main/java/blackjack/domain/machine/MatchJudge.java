package blackjack.domain.machine;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchJudge {

    BLACKJACK_WIN(Score::isBlackjackWin, 1.5),
    LOSE(Score::isLose, -1),
    DRAW(Score::isDraw, 0),
    WIN(Score::isWin, 1),
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
                .filter(matchJudge -> matchJudge.condition.test(playerScore, dealerScore))
                .findFirst()
                .orElse(WIN);
    }

    public double calculateProfit(double money) {
        return money * rate;
    }
}

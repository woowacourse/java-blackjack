package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static blackjack.domain.gameresult.Result.*;

public enum ResultJudge {

    BLACKJACK_WIN_CONDITION(ResultJudge::isBlackJackWin, BLACKJACK_WIN),
    WIN_CONDITION(ResultJudge::isWin, WIN),
    LOSE_CONDITION(ResultJudge::isLose, LOSE),
    DRAW_CONDITION(ResultJudge::isDraw, DRAW);

    private final BiPredicate<Player, Dealer> resultCondition;
    private final Result result;

    ResultJudge(BiPredicate<Player, Dealer> resultCondition, Result result) {
        this.resultCondition = resultCondition;
        this.result = result;
    }

    public static Result judge(Player player, Dealer dealer) {
        ResultJudge judgedResult = Stream.of(values())
                .filter(judge -> judge.resultCondition.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("경기의 승패가 결정되지 않습니다."));
        return judgedResult.result;
    }

    private static boolean isBlackJackWin(Player player, Dealer dealer) {
        return player.isFirstTurnBackJack() && !dealer.isBlackJack();
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBust() && !player.isBust())
                || !player.isBust() && player.hasHigherScore(dealer);
    }

    private static boolean isLose(Player player, Dealer dealer) {
        return player.isBust()
                || !player.isBust() && player.hasLowerScore(dealer);
    }

    private static boolean isDraw(Player player, Dealer dealer) {
        return (!dealer.isBust() && !player.isBust())
                && player.hasSameScore(dealer);
    }
}

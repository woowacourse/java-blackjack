package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

// Condition에 대한 판단을 옮겨보기
public enum Result {

    BLACKJACK_WIN(Result::isBlackJackWin),
    WIN(Result::isWin),
    LOSE(Result::isLose),
    DRAW(Result::isDraw);

    private final BiPredicate<Player, Dealer> resultCondition;

    Result(BiPredicate<Player, Dealer> resultCondition) {
        this.resultCondition = resultCondition;
    }

    public static Result judge(Player player, Dealer dealer) {
        return Stream.of(values())
                .filter(judge -> judge.resultCondition.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("경기의 승패가 결정되지 않습니다."));
    }

    private static boolean isBlackJackWin(Player player, Dealer dealer) {
        return player.isFirstTurnBackJack() && dealer.isNotBlackJack();
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBust() && player.isNotBust())
                || player.isNotBust() && player.hasHigherScore(dealer);
    }

    private static boolean isLose(Player player, Dealer dealer) {
        return player.isBust()
                || player.isNotBust() && player.hasLowerScore(dealer);
    }

    private static boolean isDraw(Player player, Dealer dealer) {
        return (dealer.isNotBust() && player.isNotBust())
                && player.hasSameScore(dealer);
    }
}

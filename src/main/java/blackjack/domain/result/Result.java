package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Result {

    WIN(Result::checkWin, Money::betting),
    LOSE(Result::checkLose, money -> money.betting() * Constants.LOSE_MULTIPLE),
    DRAW(Result::checkDraw, money -> Constants.DRAW_MULTIPLE),
    BLACKJACK(Result::checkBlackjack, money -> (int) (money.betting() * Constants.BLACKJACK_MULTIPLE)),
    ;

    private final BiPredicate<Player, Player> resultCalculator;
    private final Function<Money, Integer> profitCalculator;

    Result(final BiPredicate<Player, Player> resultCalculator, final Function<Money, Integer> profitCalculator) {
        this.resultCalculator = resultCalculator;
        this.profitCalculator = profitCalculator;
    }

    public static Result calculateResult(final Player dealer, final Player participant) {
        return Arrays.stream(values())
                .filter(result -> result.resultCalculator.test(dealer, participant))
                .findFirst().orElseThrow(() -> new RuntimeException("[ERROR] 결과를 찾을 수 없습니다."));
    }

    private static boolean checkWin(final Player dealer, final Player participant) {
        if (participant.isBust()) {
            return false;
        }
        return dealer.isBust() || participant.isScoreGreaterThan(dealer);
    }

    private static boolean checkLose(final Player dealer, final Player participant) {
        if (participant.isBust()) {
            return true;
        }
        return dealer.isScoreGreaterThan(participant);
    }

    private static boolean checkDraw(final Player dealer, final Player participant) {
        if (bothBlackjack(dealer, participant)) {
            return true;
        }

        return bothNotBust(dealer, participant) && bothNotBlackjack(dealer, participant) && participant.isSameScore(dealer);
    }

    private static boolean bothBlackjack(final Player dealer, final Player participant) {
        return dealer.isBlackjack() && participant.isBlackjack();
    }

    private static boolean bothNotBust(final Player dealer, final Player participant) {
        return !dealer.isBust() && !participant.isBust();
    }

    private static boolean bothNotBlackjack(final Player dealer, final Player participant) {
        return !dealer.isBlackjack() && !participant.isBlackjack();
    }

    private static boolean checkBlackjack(final Player dealer, final Player participant) {
        return !dealer.isBlackjack() && participant.isBlackjack();
    }

    public int calculateProfit(final Money money) {
        return this.profitCalculator.apply(money);
    }

    public static int calculateOppositeProfit(final int profit) {
        return profit * -1;
    }

    private static class Constants {
        private static final int LOSE_MULTIPLE = -1;
        private static final double BLACKJACK_MULTIPLE = 1.5;
        private static final int DRAW_MULTIPLE = 0;
    }
}

package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Player;

import java.util.function.Function;

public enum Result {

    WIN(Money::betting),
    LOSE(money -> money.betting() * -1),
    DRAW(money -> 0),
    BLACKJACK(money -> (int) (money.betting() * 1.5)),
    ;

    public static final int BLACKJACK_SCORE = 21;

    private final Function<Money, Integer> profitCalculator;

    Result(final Function<Money, Integer> profitCalculator) {
        this.profitCalculator = profitCalculator;
    }

    public static int calculateProfit(Money money, Result result) {
        return result.profitCalculator.apply(money);
    }

    public static int calculateOppositeProfit(int profit) {
        return profit * -1;
    }

    public static Result calculateResult(Player dealer, Player participant) {
        if (checkDraw(dealer, participant)) {
            return Result.DRAW;
        }
        if (participant.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (isParticipantWin(dealer, participant)) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    private static boolean checkDraw(Player dealer, Player participant) {
        if (dealer.isBlackjack() && participant.isBlackjack()) {
            return true;
        }

        return bothNotBust(dealer, participant) && bothNotBlackjack(dealer, participant) && isScoreSame(dealer, participant);
    }

    private static boolean bothNotBust(Player dealer, Player participant) {
        return dealer.isNotBust() && participant.isNotBust();
    }

    private static boolean bothNotBlackjack(Player dealer, Player participant) {
        return !dealer.isBlackjack() && !participant.isBlackjack();
    }

    private static boolean isScoreSame(Player dealer, Player participant) {
        return dealer.calculateFinalScore() == participant.calculateFinalScore();
    }

    private static boolean isParticipantWin(final Player dealer, final Player participant) {
        return !dealer.isNotBust() || participant.isNotBust() && participant.isGreaterThan(dealer);
    }
}

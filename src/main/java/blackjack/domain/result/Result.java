package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN(Result::playerBlackJack, Result::winCondition),
    LOSE(Result::dealerBlackJack, Result::lossCondition),
    DRAW(Result::bothBlackJack, Result::drawCondition);

    private final BiPredicate<Player, Dealer> blackJackCheck;
    private final BiPredicate<Player, Dealer> scoreCheck;

    Result(final BiPredicate<Player, Dealer> blackJackCheck, final BiPredicate<Player, Dealer> scoreCheck) {
        this.blackJackCheck = blackJackCheck;
        this.scoreCheck = scoreCheck;
    }

    public static Result getPlayerResult(final Player player, final Dealer dealer) {
        return Arrays.stream(Result.values())
                .filter(result -> result.blackJackCheck.test(player, dealer))
                .findAny()
                .orElseGet(() ->
                        Arrays.stream(Result.values())
                                .filter(result -> result.scoreCheck.test(player, dealer))
                                .findAny()
                                .orElseThrow(() -> new RuntimeException("승패를 가리지 못합니다."))
                );
    }

    private static boolean playerBlackJack(final Player player, final Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean dealerBlackJack(final Player player, final Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean bothBlackJack(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean winCondition(final Player player, final Dealer dealer) {
        return dealer.isBust() && !player.isBust() ||
                !player.isBust() && scoreBiggerThan(player, dealer);
    }

    private static boolean lossCondition(final Player player, final Dealer dealer) {
        return player.isBust() ||
                (!dealer.isBust() && scoreSmallerThan(player, dealer));
    }

    private static boolean drawCondition(final Player player, final Dealer dealer) {
        return player.getScore() == dealer.getScore();
    }

    private static boolean scoreBiggerThan(final Player player, final Dealer dealer) {
        return player.getScore() > dealer.getScore();
    }

    private static boolean scoreSmallerThan(final Player player, final Dealer dealer) {
        return player.getScore() < dealer.getScore();
    }
}

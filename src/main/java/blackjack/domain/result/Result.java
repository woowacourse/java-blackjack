package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN(Result::challengerBlackJack, Result::winCondition),
    LOSE(Result::dealerBlackJack, Result::lossCondition),
    DRAW(Result::bothBlackJack, Result::drawCondition);

    private final BiPredicate<Challenger, Dealer> blackJackCheck;
    private final BiPredicate<Challenger, Dealer> scoreCheck;

    Result(final BiPredicate<Challenger, Dealer> blackJackCheck, final BiPredicate<Challenger, Dealer> scoreCheck) {
        this.blackJackCheck = blackJackCheck;
        this.scoreCheck = scoreCheck;
    }

    public static Result getChallengerResult(final Challenger challenger, final Dealer dealer) {
        return Arrays.stream(Result.values())
                .filter(result -> result.blackJackCheck.test(challenger, dealer))
                .findAny()
                .orElseGet(() ->
                        Arrays.stream(Result.values())
                                .filter(result -> result.scoreCheck.test(challenger, dealer))
                                .findAny()
                                .orElseThrow(() -> new RuntimeException("승패를 가리지 못합니다."))
                );
    }

    private static boolean challengerBlackJack(final Challenger challenger, final Dealer dealer) {
        return challenger.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean dealerBlackJack(final Challenger challenger, final Dealer dealer) {
        return !challenger.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean bothBlackJack(final Challenger challenger, final Dealer dealer) {
        return challenger.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean winCondition(final Challenger challenger, final Dealer dealer) {
        return dealer.isBust() && !challenger.isBust() ||
                !challenger.isBust() && scoreBiggerThan(challenger, dealer);
    }

    private static boolean lossCondition(final Challenger challenger, final Dealer dealer) {
        return challenger.isBust() ||
                (!dealer.isBust() && scoreSmallerThan(challenger, dealer));
    }

    private static boolean drawCondition(final Challenger challenger, final Dealer dealer) {
        return challenger.getScore() == dealer.getScore();
    }

    private static boolean scoreBiggerThan(final Challenger challenger, final Dealer dealer) {
        return challenger.getScore() > dealer.getScore();
    }

    private static boolean scoreSmallerThan(final Challenger challenger, final Dealer dealer) {
        return challenger.getScore() < dealer.getScore();
    }
}

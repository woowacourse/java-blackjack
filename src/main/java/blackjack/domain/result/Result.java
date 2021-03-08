package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승",
            (challenger, dealer) -> challenger.isBlackJack() && !dealer.isBlackJack(),
            (challenger, dealer) -> winCondition(challenger, dealer)),
    LOSE("패",
            (challenger, dealer) -> !challenger.isBlackJack() && dealer.isBlackJack(),
            (challenger, dealer) -> lossCondition(challenger, dealer)),
    DRAW("무",
            (challenger, dealer) -> challenger.isBlackJack() && dealer.isBlackJack(),
            (challenger, dealer) -> drawCondition(challenger, dealer));

    private final String result;
    private final BiPredicate<Challenger, Dealer> blackJackCheck;
    private final BiPredicate<Challenger, Dealer> scoreCheck;

    Result(final String result,
           final BiPredicate<Challenger, Dealer> blackJackCheck, final BiPredicate<Challenger, Dealer> scoreCheck) {
        this.result = result;
        this.blackJackCheck = blackJackCheck;
        this.scoreCheck = scoreCheck;
    }

    public static Result getPlayerResult(final Challenger challenger, final Dealer dealer) {
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

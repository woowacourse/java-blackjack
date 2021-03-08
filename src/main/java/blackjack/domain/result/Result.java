package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승",
            (challenger, dealer) -> challenger.isBlackJack() && !dealer.isBlackJack(),
            (challenger, dealer) -> dealer.isBust() && !challenger.isBust() ||
                    !challenger.isBust() && scoreBiggerThan(challenger, dealer)),
    LOSE("패",
            (challenger, dealer) -> !challenger.isBlackJack() && dealer.isBlackJack(),
            (challenger, dealer) -> challenger.isBust() || (!dealer.isBust() && scoreSmallerThan(challenger, dealer))),
    DRAW("무",
            (challenger, dealer) -> challenger.isBlackJack() && dealer.isBlackJack(),
            (challenger, dealer) -> scoreEquals(challenger, dealer));

    private final String result;
    private final BiPredicate<Challenger, Dealer> blackJackCheck;
    private final BiPredicate<Challenger, Dealer> scoreCheck;

    Result(final String result,
           BiPredicate<Challenger, Dealer> blackJackCheck, BiPredicate<Challenger, Dealer> scoreCheck) {
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
                                .orElseThrow(() -> new IllegalArgumentException("승패 못가림"))
                );
    }

    public static boolean scoreBiggerThan(Challenger challenger, Dealer dealer) {
        return challenger.getScore() > dealer.getScore();
    }

    public static boolean scoreSmallerThan(Challenger challenger, Dealer dealer) {
        return challenger.getScore() < dealer.getScore();
    }

    public static boolean scoreEquals(Challenger challenger, Dealer dealer) {
        return challenger.getScore() == dealer.getScore();
    }
}

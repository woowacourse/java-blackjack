package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    BLACKJACK_WIN(1.5, (dealer, participant) ->
            participant.isBlackJack() && !dealer.isBlackJack()
    ),
    WIN(1, (dealer, participant) ->
            (dealer.getTotal() < participant.getTotal() && !participant.isBust())
                    || (dealer.isBust() && !participant.isBust())),

    DRAW(0, (dealer, participant) ->
            (!dealer.isBlackJack() && (dealer.getTotal() == participant.getTotal()) && !participant.isBust())
                    || (dealer.isBlackJack() && participant.isBlackJack())),

    LOSE(-1, (dealer, participant) ->
            (dealer.getTotal() > participant.getTotal() && !dealer.isBust())
                    || participant.isBust()
                    || (dealer.isBust() && !participant.isBlackJack())
                    || dealer.isBlackJack() && !participant.isBlackJack());

    private final double yield;
    private final BiPredicate<Score, Score> condition;

    Result(final double yield, final BiPredicate<Score, Score> condition) {
        this.yield = yield;
        this.condition = condition;
    }

    public static Result decide(final Score dealerScore, final Score participantScore) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealerScore, participantScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public double getYield() {
        return yield;
    }
}

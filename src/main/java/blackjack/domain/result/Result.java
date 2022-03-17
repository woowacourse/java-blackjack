package blackjack.domain.result;

import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승", (dealer, participant) ->
            (dealer.getTotal() < participant.getTotal() && !participant.isBust())
                    || (dealer.isBust() && !participant.isBust())
                    || !dealer.isBlackJack() && participant.isBlackJack()),

    DRAW("무승부", (dealer, participant) ->
            (!dealer.isBlackJack() && (dealer.getTotal() == participant.getTotal()) && !participant.isBust())
                    || (dealer.isBlackJack() && participant.isBlackJack())),

    LOSE("패", (dealer, participant) ->
            (dealer.getTotal() > participant.getTotal() && !dealer.isBust())
                    || participant.isBust()
                    || (dealer.isBust() && !participant.isBlackJack())
                    || dealer.isBlackJack() && !participant.isBlackJack());

    private final String name;
    private final BiPredicate<Score, Score> condition;

    Result(final String name, final BiPredicate<Score, Score> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Result decide(final Score dealerScore, final Score participantScore) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealerScore, participantScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public Result getOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return this.name;
    }
}

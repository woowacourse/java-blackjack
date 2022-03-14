package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승", (dealer, participant) ->
            participant.isNotBust() &&
                    (participant.getTotalScore() > dealer.getTotalScore() || dealer.isBust())),
    DRAW("무승부", (dealer, participant) ->
            dealer.isNotBlackjack() &&
                    participant.isNotBust() && dealer.getTotalScore() == participant.getTotalScore()),
    LOSE("패", (dealer, participant) ->
            dealer.isBlackjack() || participant.isBust() ||
                    (dealer.isNotBust() && dealer.getTotalScore() > participant.getTotalScore())),
    ;

    private final String name;
    private final BiPredicate<Dealer, Participant> condition;

    Result(final String name, final BiPredicate<Dealer, Participant> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Result of(final Dealer dealer, final Participant participant) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealer, participant))
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

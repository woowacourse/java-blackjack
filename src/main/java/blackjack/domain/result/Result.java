package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    BLACKJACK("블랙잭", ((dealer, guest) ->
            guest.isBlackjack() && dealer.isNotBlackjack())),
    WIN("승", (dealer, guest) ->
            guest.isNotBust() &&
                    (guest.getTotalScore() > dealer.getTotalScore() || dealer.isBust())),
    DRAW("무승부", (dealer, guest) ->
            dealer.isNotBlackjack() &&
                    guest.isNotBust() && dealer.getTotalScore() == guest.getTotalScore()),
    LOSE("패", (dealer, guest) ->
            dealer.isBlackjack() || guest.isBust() ||
                    (dealer.isNotBust() && dealer.getTotalScore() > guest.getTotalScore())),
    ;

    private final String name;
    private final BiPredicate<Dealer, Guest> condition;

    Result(final String name, final BiPredicate<Dealer, Guest> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Result of(final Dealer dealer, final Guest guest) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealer, guest))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public String getName() {
        return this.name;
    }
}

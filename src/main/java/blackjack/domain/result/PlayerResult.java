package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public enum PlayerResult {

    WIN("승", (player, dealer) -> (dealer.isBust() && !player.isBust()) ||
            (player.isBlackjack() && !dealer.isBlackjack()) ||
            (player.hasHigherScoreThan(dealer) && !player.isBust())),

    DRAW("무", (player, dealer) -> (dealer.isBlackjack() && player.isBlackjack()) ||
            (dealer.hasSameScoreWith(player)) && !player.isBust() && !dealer.isBlackjack() && !player.isBlackjack()),

    LOSS("패", (player, dealer) -> (player.isBust()) ||
            (!player.isBlackjack() && dealer.isBlackjack()) ||
            (dealer.hasHigherScoreThan(player)));

    private final String name;
    private final BiPredicate<Player, Dealer> predicate;

    PlayerResult(final String name, final BiPredicate<Player, Dealer> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public static PlayerResult of(final Player player, final Dealer dealer) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 결과입니다."));
    }

    public String getName() {
        return name;
    }
}

package blackjack.domain.result;

import blackjack.domain.playing.user.Dealer;
import blackjack.domain.playing.user.Player;
import blackjack.domain.result.Exception.PlayerResultException;

import java.util.Arrays;
import java.util.function.BiFunction;

import static blackjack.domain.result.ResultType.*;

public enum PlayerResult {
    PLAYER_IS_BUST((player, dealer) -> player.isBust(), LOSE),
    DEALER_IS_BUST((player, dealer) -> player.isNotBust() && dealer.isBust(), WIN),
    PLAYER_SCORE_IS_GREATER(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isOverScore(dealer)), WIN),
    PLAYER_SCORE_IS_SAME(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isSameScore(dealer)), DRAW),
    PLAYER_SCORE_IS_LOWER(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isUnderScore(dealer)), LOSE);

    private BiFunction<Player, Dealer, Boolean> condition;
    private ResultType result;

    PlayerResult(BiFunction<Player, Dealer, Boolean> condition, ResultType result) {
        this.condition = condition;
        this.result = result;
    }

    public static ResultType of(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(rule -> rule.apply(player, dealer))
                .findFirst()
                .orElseThrow(PlayerResultException::new)
                .result;
    }

    private Boolean apply(Player player, Dealer dealer) {
        return condition.apply(player, dealer);
    }
}

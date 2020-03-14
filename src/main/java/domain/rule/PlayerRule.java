package domain.rule;

import java.util.Arrays;
import java.util.function.BiFunction;

import domain.result.ResultType;
import domain.user.User;

public enum PlayerRule {

    PLAYER_BUST((player, dealer) -> player.isBust(), ResultType.LOSE),
    DEALER_BUST((player, dealer) -> dealer.isBust(), ResultType.WIN),
    PLAYER_GREATER((player, dealer) -> player.calculatePoint() > dealer.calculatePoint(), ResultType.WIN),
    DEALER_GREATER((player, dealer) -> player.calculatePoint() < dealer.calculatePoint(), ResultType.LOSE),
    PLAYER_ONLY_BLACK_JACK((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), ResultType.WIN),
    DEALER_ONLY_BLACK_JACK((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), ResultType.LOSE),
    EQUALS((player, dealer) -> player.calculatePoint() == dealer.calculatePoint(), ResultType.DRAW);

    private final BiFunction<User, User, Boolean> condition;
    private final ResultType resultType;

    PlayerRule(BiFunction<User, User, Boolean> condition, ResultType resultType) {
        this.condition = condition;
        this.resultType = resultType;
    }

    public static ResultType decideResultType(User player, User dealer) {
        return Arrays.stream(PlayerRule.values())
                .filter(playerRule -> playerRule.condition.apply(player, dealer))
                .findFirst()
                .get()
                .resultType;
    }
}

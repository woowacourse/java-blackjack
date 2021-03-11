package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum MatchRule {
    BUST_PLAYER((player, dealer) -> player.isBust(), ResultType.LOSE),
    BUST_DEALER((player, dealer) -> !player.isBust() && dealer.isBust(), ResultType.WIN),
    BLACKJACK_PLAYER_WIN((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), ResultType.WIN),
    BLACKJACK_DEALER_WIN((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), ResultType.LOSE),
    BLACKJACK_DRAW((player, dealer) -> player.isBlackJack() && dealer.isBlackJack(), ResultType.DRAW),
    SCORE_COMPARISON_PLAYER_WIN((player, dealer) -> player.getScore() > dealer.getScore(), ResultType.WIN),
    SCORE_COMPARISON_DEALER_WIN((player, dealer) -> player.getScore() < dealer.getScore(), ResultType.LOSE),
    SCORE_COMPARISON_DRAW((player, dealer) -> player.getScore() == dealer.getScore(), ResultType.DRAW);

    private final BiPredicate<Player, Dealer> condition;
    private final ResultType matchResult;

    MatchRule(BiPredicate<Player, Dealer> condition, ResultType matchResult) {
        this.condition = condition;
        this.matchResult = matchResult;
    }

    public static ResultType getMatchResult(Player player, Dealer dealer) {
        return Stream.of(MatchRule.values())
                .filter(matchRule -> matchRule.condition.test(player, dealer))
                .findAny()
                .map(matchRule -> matchRule.matchResult)
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 승부 결과가 없습니다."));
    }
}

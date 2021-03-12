package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum MatchRule {
    BLACKJACK_PLAYER_WIN(MatchRule::isOnlyPlayerBlackJack, ResultType.WIN),
    BLACKJACK_DEALER_WIN(MatchRule::isOnlyDealerBlackJack, ResultType.LOSE),
    BLACKJACK_DRAW(MatchRule::isBothBlackJack, ResultType.DRAW),
    BUST_PLAYER(MatchRule::isPlayerBust, ResultType.LOSE),
    BUST_DEALER(MatchRule::isOnlyDealerBust, ResultType.WIN),
    SCORE_COMPARISON_PLAYER_WIN(MatchRule::isPlayerScoreHigher, ResultType.WIN),
    SCORE_COMPARISON_DEALER_WIN(MatchRule::isDealerScoreHigher, ResultType.LOSE),
    SCORE_COMPARISON_DRAW(MatchRule::isBothScoreEquals, ResultType.DRAW);

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

    private static boolean isPlayerBust(Player player, Dealer dealer) {
        return player.isBust();
    }

    private static boolean isOnlyDealerBust(Player player, Dealer dealer) {
        return !player.isBust() && dealer.isBust();
    }

    private static boolean isOnlyPlayerBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean isOnlyDealerBlackJack(Player player, Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean isBothBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }
    private static boolean isPlayerScoreHigher(Player player, Dealer dealer) {
        return player.getScore() > dealer.getScore();
    }

    private static boolean isDealerScoreHigher(Player player, Dealer dealer) {
        return player.getScore() < dealer.getScore();
    }

    private static boolean isBothScoreEquals(Player player, Dealer dealer) {
        return player.getScore() == dealer.getScore();
    }
}

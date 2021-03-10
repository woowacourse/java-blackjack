package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum Result {
    DRAW("무승부"),
    WIN("승리"),
    BLACKJACK_WIN("블랙잭 승리"),
    LOSE("패배");

    private final String resultName;

    Result(String resultName) {
        this.resultName = resultName;
    }

    public static Result of(Dealer dealer, Player player) {
        return findResultByBlackjack(dealer, player);
    }

    private static Result findResultByBlackjack(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && player.isNotBlackjack()) {
            return Result.LOSE;
        }

        if (dealer.isBlackjack()) {
            return Result.DRAW;
        }

        if (player.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }

        return findResultByScore(dealer, player);
    }

    private static Result findResultByScore(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isNotBust()) {
            return Result.WIN;
        }

        if (dealer.isBiggerScoreThan(player) || player.isBust()) {
            return Result.LOSE;
        }

        if (dealer.isEqualScore(player)) {
            return Result.DRAW;
        }

        return Result.WIN;
    }
}

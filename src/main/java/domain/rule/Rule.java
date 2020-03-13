package domain.rule;

import domain.result.WinningResult;
import domain.user.Dealer;
import domain.user.Player;

public class Rule {

    public static WinningResult decideWinningResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return WinningResult.LOSE;
        }
        if (dealer.isBust()) {
            return WinningResult.WIN;
        }
        return bothNotBustCase(dealer, player);
    }

    private static WinningResult bothNotBustCase(Dealer dealer, Player player) {
        if (player.calculatePoint() > dealer.calculatePoint()) {
            return WinningResult.WIN;
        }
        if (player.calculatePoint() == dealer.calculatePoint()) {
            return checkBlackJackCase(dealer, player);
        }
        return WinningResult.LOSE;
    }

    private static WinningResult checkBlackJackCase(Dealer dealer, Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return WinningResult.WIN;
        }
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}

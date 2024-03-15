package blackjack.util;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class JudgeUtil {

    private JudgeUtil() {
    }

    public static GameResult judge(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return judgeWhenDealerBust(player);
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack(player);
        }

        return judgeWhenDealerNormal(dealer, player);
    }

    public static GameResult judgeWhenDealerBust(final Player player) {
        if (player.isBust()) {
            return GameResult.DRAW;
        }

        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }

        return GameResult.WIN;
    }

    public static GameResult judgeWhenDealerBlackjack(Player player) {
        if (player.isBlackjack()) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }

    public static GameResult judgeWhenDealerNormal(Dealer dealer, final Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }

        return judgeWhenNormalTogether(dealer, player);
    }

    private static GameResult judgeWhenNormalTogether(final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return GameResult.DRAW;
        }

        if (dealer.getScore() > player.getScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }
}

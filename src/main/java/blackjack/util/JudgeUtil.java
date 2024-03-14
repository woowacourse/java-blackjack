package blackjack.util;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.ProfitResult;

public class JudgeUtil {

    private JudgeUtil() {
    }

    public static void judge(final ProfitResult profitResult, final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            judgeWhenDealerBust(profitResult, player);
            return;
        }

        if (dealer.isBlackjack()) {
            judgeWhenDealerBlackjack(profitResult, player);
            return;
        }

        judgeWhenDealerNormal(profitResult, dealer, player);
    }

    public static void judgeWhenDealerBust(final ProfitResult profitResult, final Player player) {
        if (player.isBust()) {
            draw(profitResult, player);
            return;
        }

        if (player.isBlackjack()) {
            playerBlackjackWin(profitResult, player);
            return;
        }

        playerWin(profitResult, player);
    }

    public static void judgeWhenDealerBlackjack(final ProfitResult profitResult, Player player) {
        if (player.isBlackjack()) {
            draw(profitResult, player);
            return;
        }

        playerLose(profitResult, player);
    }

    public static void judgeWhenDealerNormal(final ProfitResult profitResult, Dealer dealer, final Player player) {
        if (player.isBust()) {
            playerLose(profitResult, player);
            return;
        }

        if (player.isBlackjack()) {
            playerBlackjackWin(profitResult, player);
            return;
        }

        judgeWhenNormalTogether(profitResult, dealer, player);
    }

    private static void judgeWhenNormalTogether(final ProfitResult profitResult, final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            draw(profitResult, player);
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            playerLose(profitResult, player);
            return;
        }

        playerWin(profitResult, player);
    }

    private static void playerLose(final ProfitResult profitResult, final Player player) {
        profitResult.addProfitResult(player, GameResult.LOSE);
    }

    private static void playerBlackjackWin(final ProfitResult profitResult, final Player player) {
        profitResult.addProfitResult(player, GameResult.BLACKJACK);
    }

    private static void playerWin(final ProfitResult profitResult, final Player player) {
        profitResult.addProfitResult(player, GameResult.WIN);
    }

    private static void draw(final ProfitResult profitResult, final Player player) {
        profitResult.addProfitResult(player, GameResult.DRAW);
    }
}

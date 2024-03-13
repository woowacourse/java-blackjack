package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.Profits;

public class Judge {

    private Judge() {
    }

    public static void judge(final Profits profits, final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            judgeWhenDealerBust(player, profits);
            return;
        }

        if (dealer.isBlackjack()) {
            judgeWhenDealerBlackjack(player, profits);
            return;
        }

        judgeWhenDealerNormal(profits, dealer, player);
    }

    public static void judgeWhenDealerBust(final Player player, final Profits profits) {
        if (player.isBust()) {
            draw(profits, player);
            return;
        }

        playerWin(profits, player);
    }

    public static void judgeWhenDealerBlackjack(
            final Player player, final Profits profits) {
        if (player.isBlackjack()) {
            draw(profits, player);
            return;
        }

        playerLose(profits, player);
    }

    public static void judgeWhenDealerNormal(
            final Profits profits, final Dealer dealer, final Player player) {
        if (player.isBust()) {
            playerLose(profits, player);
            return;
        }

        if (player.isBlackjack()) {
            playerWin(profits, player);
            return;
        }

        judgeWhenNormalTogether(profits, dealer, player);
    }

    private static void judgeWhenNormalTogether(
            final Profits profits, final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            draw(profits, player);
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            playerLose(profits, player);
            return;
        }

        playerWin(profits, player);
    }

    private static void playerLose(
            final Profits profits, final Player player) {
        profits.addProfit(player, GameResult.LOSE);
    }

    private static void playerWin(
            final Profits profits, final Player player) {
        profits.addProfit(player, GameResult.WIN);
    }

    private static void draw(
            final Profits profits, final Player player) {
        profits.addProfit(player, GameResult.DRAW);
    }
}

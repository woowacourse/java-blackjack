package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerResult;

public class Judge {

    private Judge() {
    }

    public static void judge(final Player player, final Dealer dealer, final PlayerResult playerResult) {
        if (dealer.isBust()) {
            judgeWhenDealerBust(playerResult, player);
            return;
        }

        if (dealer.isBlackjack()) {
            judgeWhenDealerBlackjack(playerResult, player);
            return;
        }

        judgeWhenDealerNormal(dealer, playerResult, player);
    }

    public static void judgeWhenDealerBust(final PlayerResult playerResult, final Player player) {
        if (player.isBust()) {
            draw(playerResult, player);
            return;
        }

        playerWin(playerResult, player);
    }

    public static void judgeWhenDealerBlackjack(
            final PlayerResult playerResult, final Player player) {
        if (player.isBlackjack()) {
            draw(playerResult, player);
            return;
        }

        playerLose(playerResult, player);
    }

    public static void judgeWhenDealerNormal(
            final Dealer dealer, final PlayerResult playerResult, final Player player) {
        if (player.isBust()) {
            playerLose(playerResult, player);
            return;
        }

        if (player.isBlackjack()) {
            playerWin(playerResult, player);
            return;
        }

        judgeWhenNormalTogether(dealer, playerResult, player);
    }

    private static void judgeWhenNormalTogether(
            final Dealer dealer, final PlayerResult playerResult, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            draw(playerResult, player);
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            playerLose(playerResult, player);
            return;
        }

        playerWin(playerResult, player);
    }

    private static void playerLose(
            final PlayerResult playerResult, final Player player) {
        playerResult.addResult(player, GameResult.LOSE);
    }

    private static void playerWin(
            final PlayerResult playerResult, final Player player) {
        playerResult.addResult(player, GameResult.WIN);
    }

    private static void draw(
            final PlayerResult playerResult, final Player player) {
        playerResult.addResult(player, GameResult.DRAW);
    }
}

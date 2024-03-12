package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;

public class Judge {

    private Judge() {
    }

    public static DealerResult judge(
            final DealerResult dealerResult, final Player player, final Dealer dealer,
            final PlayerResult playerResult) {
        if (dealer.isBust()) {
            return judgeWhenDealerBust(playerResult, player, dealerResult);
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack(playerResult, player, dealerResult);
        }

        return judgeWhenDealerNormal(dealer, playerResult, player, dealerResult);
    }

    public static DealerResult judgeWhenDealerBust(
            final PlayerResult playerResult, final Player player, final DealerResult dealerResult) {
        if (player.isBust()) {
            return draw(playerResult, player, dealerResult);
        }

        return dealerLose(playerResult, player, dealerResult);
    }

    public static DealerResult judgeWhenDealerBlackjack(
            final PlayerResult playerResult, final Player player, final DealerResult dealerResult) {
        if (player.isBlackjack()) {
            return draw(playerResult, player, dealerResult);
        }

        return dealerWins(playerResult, player, dealerResult);
    }

    public static DealerResult judgeWhenDealerNormal(
            final Dealer dealer, final PlayerResult playerResult, final Player player,
            final DealerResult dealerResult) {
        if (player.isBust()) {
            return dealerWins(playerResult, player, dealerResult);
        }

        if (player.isBlackjack()) {
            return dealerLose(playerResult, player, dealerResult);
        }

        return judgeWhenNormalTogether(dealer, playerResult, player, dealerResult);
    }

    private static DealerResult judgeWhenNormalTogether(
            final Dealer dealer, final PlayerResult playerResult, final Player player,
            final DealerResult dealerResult) {
        if (dealer.isSameScore(player.getScore())) {
            return draw(playerResult, player, dealerResult);
        }

        if (dealer.getScore() > player.getScore()) {
            return dealerWins(playerResult, player, dealerResult);
        }

        return dealerLose(playerResult, player, dealerResult);
    }

    private static DealerResult dealerWins(
            final PlayerResult playerResult, final Player player, final DealerResult dealerResult) {
        playerResult.addResult(player, GameResult.LOSE);
        return DealerResult.createByGameResult(GameResult.WIN, dealerResult);
    }

    private static DealerResult dealerLose(
            final PlayerResult playerResult, final Player player, final DealerResult dealerResult) {
        playerResult.addResult(player, GameResult.WIN);
        return DealerResult.createByGameResult(GameResult.LOSE, dealerResult);
    }

    private static DealerResult draw(
            final PlayerResult playerResult, final Player player, final DealerResult dealerResult) {
        playerResult.addResult(player, GameResult.DRAW);
        return DealerResult.createByGameResult(GameResult.DRAW, dealerResult);
    }
}

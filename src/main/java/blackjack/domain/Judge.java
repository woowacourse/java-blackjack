package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;

public class Judge {

    private Judge() {
    }

    public static DealerResult judge(
            final ResultStatus resultStatus, final Player player, final Dealer dealer, final PlayerResult playerResult) {
        if (dealer.isBust()) {
            return judgeWhenDealerBust(playerResult, player, resultStatus);
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack(playerResult, player, resultStatus);
        }

        return judgeWhenDealerNormal(dealer, playerResult, player, resultStatus);
    }

    public static DealerResult judgeWhenDealerBust(
            final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        if (player.isBust()) {
            return draw(playerResult, player, resultStatus);
        }

        if (player.isBlackjack()) {
            return dealerLose(playerResult, player, resultStatus);
        }

        return dealerLose(playerResult, player, resultStatus);
    }

    public static DealerResult judgeWhenDealerBlackjack(
            final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        if (player.isBust()) {
            return dealerWins(playerResult, player, resultStatus);
        }

        if (player.isBlackjack()) {
            return draw(playerResult, player, resultStatus);
        }

        return dealerWins(playerResult, player, resultStatus);
    }

    public static DealerResult judgeWhenDealerNormal(
            final Dealer dealer, final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        if (player.isBust()) {
            return dealerWins(playerResult, player, resultStatus);
        }

        if (player.isBlackjack()) {
            return dealerLose(playerResult, player, resultStatus);
        }

        return judgeWhenNormalTogether(dealer, playerResult, player, resultStatus);
    }

    private static DealerResult judgeWhenNormalTogether(
            final Dealer dealer, final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        if(dealer.isSameScore(player.getScore())){
            return draw(playerResult, player, resultStatus);
        }

        if(dealer.getScore() > player.getScore()){
            return dealerWins(playerResult, player, resultStatus);
        }

        return dealerLose(playerResult, player, resultStatus);
    }

    private static DealerResult dealerLose(
            final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        playerResult.addResult(player, GameResult.WIN);
        return new DealerResult(resultStatus.getWins(), resultStatus.getLoses() + 1, resultStatus.getDraws());
    }

    private static DealerResult dealerWins(
            final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        playerResult.addResult(player, GameResult.LOSE);
        return new DealerResult(resultStatus.getWins() + 1, resultStatus.getLoses(), resultStatus.getDraws());
    }

    private static DealerResult draw(
            final PlayerResult playerResult, final Player player, final ResultStatus resultStatus) {
        playerResult.addResult(player, GameResult.DRAW);
        return new DealerResult(resultStatus.getWins(), resultStatus.getLoses(), resultStatus.getDraws() + 1);
    }
}

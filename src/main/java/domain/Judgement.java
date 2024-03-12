package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class Judgement {

    private final DealerResult dealerResult;
    private final PlayerResults playerResults;

    private Judgement(final DealerResult dealerResult, final PlayerResults playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public static Judgement of(final Dealer dealer, final Players players) {
        PlayerResults playerResults = new PlayerResults();
        DealerResult dealerResult = new DealerResult();
        for (Player player : players.getPlayers()) {
            Result result = judgeOnDealerBasis(dealer, player, playerResults);
            dealerResult = dealerResult.addResult(result);
        }
        return new Judgement(dealerResult, playerResults);
    }

    private static Result judgeOnDealerBasis(final Dealer dealer, final Player player, PlayerResults playerResults) {
        if (dealer.isBust()) {
            return isDealerBust(player, playerResults);
        }
        if (dealer.isBlackJack()) {
            return isDealerBlackJack(player, playerResults);
        }
        return isDealerNormalScore(dealer, player, playerResults);
    }

    private static Result isDealerBust(final Player player, PlayerResults playerResults) {
        if (!player.isBust()) {
            playerWin(player, playerResults);
            return Result.LOSE;
        }
        playerLose(player, playerResults);
        return Result.WIN;
    }

    private static Result isDealerBlackJack(final Player player, PlayerResults playerResults) {
        if (player.isBlackJack()) {
            playerTie(player, playerResults);
            return Result.TIE;
        }
        playerLose(player, playerResults);
        return Result.WIN;
    }

    private static Result isDealerNormalScore(final Dealer dealer, final Player player, PlayerResults playerResults) {
        if (player.isBlackJack()) {
            playerWin(player, playerResults);
            return Result.LOSE;
        }
        if (player.isNormalScore()) {
            return judeByNormalScore(dealer, player, playerResults);
        }
        playerLose(player, playerResults);
        return Result.WIN;
    }

    private static Result judeByNormalScore(final Dealer dealer, final Player player, PlayerResults playerResults) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();
        if (dealerScore > playerScore) {
            playerLose(player, playerResults);
            return Result.WIN;
        }
        if (dealerScore < playerScore) {
            playerWin(player, playerResults);
            return Result.LOSE;
        }
        playerTie(player, playerResults);
        return Result.TIE;
    }

    private static void playerWin(final Player player, PlayerResults playerResults) {
        playerResults.addResult(player, Result.WIN);
    }

    private static void playerTie(final Player player, PlayerResults playerResults) {
        playerResults.addResult(player, Result.TIE);
    }

    private static void playerLose(final Player player, PlayerResults playerResults) {
        playerResults.addResult(player, Result.LOSE);
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }
}

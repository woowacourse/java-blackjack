package blackjack.model;

import java.util.List;

public class GameResultCalculator {
    public void calculate(List<GameSummary> gameSummaries) {
        GameSummary dealerGameSummary = gameSummaries.getFirst();
        Dealer dealer = (Dealer) dealerGameSummary.user();
        int dealerTotalScore = dealerGameSummary.totalScore();
        List<GameSummary> playerGameSummaries = gameSummaries.subList(1, gameSummaries.size());

        for (GameSummary playerGameSummary : playerGameSummaries) {
            Player player = (Player) playerGameSummary.user();
            int playerTotalScore = playerGameSummary.totalScore();

            if (calculateWhenBlackjack(playerGameSummary, dealerGameSummary, dealer, player)) {
                continue;
            }

            if (calculateWhenBust(playerGameSummary, dealerGameSummary, dealer, player)) {
                continue;
            }

            calculateWhenNormal(dealerTotalScore, playerTotalScore, dealer, player);
        }
    }

    private static boolean calculateWhenBlackjack(GameSummary playerGameSummary, GameSummary dealerGameSummary,
                                                  Dealer dealer,
                                                  Player player) {
        if (dealerGameSummary.blackjack() && playerGameSummary.blackjack()) {
            dealer.addResult(GameResult.DRAW);
            return true;
        }

        if (playerGameSummary.blackjack()) {
            player.mark(GameResult.WIN);
            dealer.addResult(GameResult.LOSE);
            return true;
        }

        if (dealerGameSummary.blackjack()) {
            player.mark(GameResult.LOSE);
            dealer.addResult(GameResult.WIN);
            return true;
        }
        return false;
    }

    private static boolean calculateWhenBust(GameSummary playerGameSummary, GameSummary dealerGameSummary,
                                             Dealer dealer,
                                             Player player) {
        if (playerGameSummary.bust() && dealerGameSummary.bust()) {
            dealer.addResult(GameResult.WIN);
            player.mark(GameResult.LOSE);
            return true;
        }

        if (playerGameSummary.bust()) {
            player.mark(GameResult.LOSE);
            dealer.addResult(GameResult.WIN);
            return true;
        }

        if (dealerGameSummary.bust()) {
            dealer.addResult(GameResult.LOSE);
            player.mark(GameResult.WIN);
            return true;
        }
        return false;
    }

    private static void calculateWhenNormal(int dealerTotalScore, int playerTotalScore, Dealer dealer, Player player) {
        if (dealerTotalScore > playerTotalScore) {
            dealer.addResult(GameResult.WIN);
            player.mark(GameResult.LOSE);
            return;
        }

        if (dealerTotalScore < playerTotalScore) {
            dealer.addResult(GameResult.LOSE);
            player.mark(GameResult.WIN);
            return;
        }

        dealer.addResult(GameResult.DRAW);
    }
}
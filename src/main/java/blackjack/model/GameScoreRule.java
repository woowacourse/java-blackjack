package blackjack.model;

import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;

public class GameScoreRule {

    public GameScoreRule() {
    }

    public void decideWinner(Dealer dealer, Player player, GameResult gameResult) {
        if (dealerWinRule(dealer, player)) {
            decideDealerWin(player, gameResult);
            return;
        }
        if (playerWinRule(dealer, player)) {
            decidePlayerWin(player, gameResult);
            return;
        }
        decideTie(player, gameResult);
    }

    private boolean playerWinRule(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBlackJack() && dealer.isBlackJack()) {
            return true;
        }
        if (!player.isBusted() && dealer.isBusted()) {
            return true;
        }
        return (!player.isBusted() && playerScore > dealerScore);
    }

    private boolean dealerWinRule(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBusted() && dealer.isBusted()) {
            return true;
        }
        if (player.isBusted() && !dealer.isBusted()) {
            return true;
        }
        return (!dealer.isBusted() && playerScore < dealerScore);
    }

    private void decideDealerWin(Player player, GameResult gameResult) {
        gameResult.addDealerWin();
        gameResult.addPlayerLose(player);
    }

    private void decidePlayerWin(Player player, GameResult gameResult) {
        gameResult.addDealerLose();
        gameResult.addPlayerWin(player);
    }

    private void decideTie(Player player, GameResult gameResult) {
        gameResult.addDealerTie();
        gameResult.addPlayerTie(player);
    }
}

package model;

import java.util.List;

public class BettingCalculator {

    private final int BLACK_JACK = 21;
    private final double BLACK_JACK_WIN_PRICE = 0.5;
    GameResult gameResult;

    public BettingCalculator() {
        gameResult = new GameResult();
    }

    public GameResult calculateBettingMoney(Dealer dealer, Players players) {
        int dealerScore = dealer.calculateTotalScore();
        gameResult.addParticipant(dealer);
        List<Player> list = players.players();
        for (Player player : list) {
            resolveBettingResult(dealerScore, player, dealer);
            gameResult.addParticipant(player);
        }
        return gameResult;
    }

    private void resolveBettingResult(int dealerScore, Player player, Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        if (applyBlackJackWinIfSatisfied(player, dealer)) {
            return;
        }
        if (applyDealerWinIfSatisfied(player, dealer, playerScore, dealerScore)) {
            return;
        }
        if (isPlayerWin(playerScore, dealerScore)) {
            updateProfit(dealer, player, false, false);
        }
    }

    private boolean applyBlackJackWinIfSatisfied(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            updateProfit(dealer, player, false, true);
            return true;
        }
        return false;
    }

    private boolean applyDealerWinIfSatisfied(Player player, Dealer dealer, int playerScore, int dealerScore) {
        if ((!player.isBlackJack() && dealer.isBlackJack())
                || playerScore > BLACK_JACK
                || (dealerScore <= BLACK_JACK && playerScore < dealerScore)) {
            updateProfit(dealer, player, true, false);
            return true;
        }
        return false;
    }

    private boolean isPlayerWin(int playerScore, int dealerScore) {
        return dealerScore > BLACK_JACK || playerScore > dealerScore;
    }

    private void updateProfit(Dealer dealer, Player player, boolean dealerWin, boolean blackJack) {
        long money = player.bettingMoney();
        if (blackJack) {
            settleAccountsPlayerWin(dealer, player, (long) (money * BLACK_JACK_WIN_PRICE));
            return;
        } else if (dealerWin) {
            settleAccountsDealerWin(dealer, player, money);
            return;
        }
        settleAccountsPlayerWin(dealer, player, money);
    }

    private void settleAccountsPlayerWin(Dealer dealer, Player player, long money) {
        player.addProfit(money);
        dealer.subtractProfit(money);
    }

    private void settleAccountsDealerWin(Dealer dealer, Player player, long money) {
        dealer.addProfit(money);
        player.subtractProfit(money);
    }
}

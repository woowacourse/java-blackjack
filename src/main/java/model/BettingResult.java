package model;

import java.util.ArrayList;
import java.util.List;

public class BettingResult {

    private final int BLACK_JACK = 21;
    private final double BLACK_JACK_WIN_PRICE = 0.5;
    private List<Participant> participantsBettingResults;

    public BettingResult() {
        participantsBettingResults = new ArrayList<>();
    }

    public void calculateBettingMoney(Dealer dealer, List<Player> players) {
        int dealerScore = dealer.calculateTotalScore();
        participantsBettingResults.add(dealer);
        players.forEach(player -> {
            resolveBettingResult(dealerScore, player, dealer);
            participantsBettingResults.add(player);
        });
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
            player.addProfit((int) (money * BLACK_JACK_WIN_PRICE));
            dealer.subtractProfit((int) (money * BLACK_JACK_WIN_PRICE));
            return;
        } else if (dealerWin) {
            player.subtractProfit(money);
            dealer.addProfit(money);
            return;
        }
        player.addProfit(money);
        dealer.subtractProfit(money);
    }

    public List<Participant> participantsBettingResults() {
        return participantsBettingResults;
    }
}

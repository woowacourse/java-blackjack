package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingRound {
    Map<Player, Integer> initialPlayerBets = new HashMap<>();
    Map<Player, Integer> finalPlayerBets = new HashMap<>();

    public void bet(Player player, int betAmount) {
        initialPlayerBets.put(player, betAmount);
    }

    public int getPlayerInitialBetAmount(Player player) {
        return initialPlayerBets.get(player);
    }

    public int getPlayerFinalBetAmount(Player player) {
        return finalPlayerBets.get(player);
    }

    public void betIsLostIfPlayerLose(Player player) {
        finalPlayerBets.put(player, initialPlayerBets.get(player) * -1);
    }

    public void extraPayoutOnBlackjack(Player player) {
        if (player.isBlackJack()) {
            int initialBetAmount = initialPlayerBets.get(player);
            finalPlayerBets.put(player, (int) (initialBetAmount * 1.5));
        }
    }

    public void refundBetOnBlackjackPush(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            finalPlayerBets.put(player, initialPlayerBets.get(player));
        }
    }

    public void PlayersReceiveBetIfDealerBusts(Player player, Dealer dealer) {
        if (dealer.isBust() && !player.isBust() && !player.isBlackJack()) {
            finalPlayerBets.put(player, initialPlayerBets.get(player));
        }
    }

    public int getPlayerProfit(Player player) {
        return finalPlayerBets.get(player);
    }

    public void calculateProfit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            GameResult playerGameResult = GameResult.calculatePlayerGameResult(dealer, player);
            if (playerGameResult == GameResult.LOSE) {
                betIsLostIfPlayerLose(player);
            }
            if (playerGameResult == GameResult.WIN && player.isBlackJack()) {
                extraPayoutOnBlackjack(player);
            }
            if (playerGameResult == GameResult.WIN && !player.isBlackJack()) {
                finalPlayerBets.put(player, initialPlayerBets.get(player));
            }
            if (playerGameResult == GameResult.PUSH) {
                refundBetOnBlackjackPush(player, dealer);
            }
        }
    }
}

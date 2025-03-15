package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingSession {
    Map<Player, Integer> bets = new HashMap<>();
    Map<Player, Integer> earnings = new HashMap<>();

    public void bet(Player player, int betAmount) {
        bets.put(player, betAmount);
    }

    public void recordEarningsOnLoss(Player player) {
        earnings.put(player, bets.get(player) * -1);
    }

    public void recordEarningsOnWin(Player player) {
        if (player.isBlackJack()) {
            applyBlackjackBonus(player);
            return;
        }
        earnings.put(player, bets.get(player));
    }

    public void applyBlackjackBonus(Player player) {
        int initialBetAmount = bets.get(player);
        earnings.put(player, (int) (initialBetAmount * 1.5));
    }

    public void refundBetOnBlackjackPush(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            earnings.put(player, bets.get(player));
        }
    }

    public void rewardEarningsOnDealerBust(Player player, Dealer dealer) {
        if (dealer.isBust() && !player.isBust() && !player.isBlackJack()) {
            earnings.put(player, bets.get(player));
        }
    }

    public void calculateProfit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            GameResult playerGameResult = GameResult.calculatePlayerGameResult(dealer, player);
            if (playerGameResult == GameResult.LOSE) {
                recordEarningsOnLoss(player);
            }
            if (playerGameResult == GameResult.WIN) {
                recordEarningsOnWin(player);
            }
            if (playerGameResult == GameResult.PUSH) {
                refundBetOnBlackjackPush(player, dealer);
            }
        }
    }

    public int getBetAmount(Player player) {
        return bets.get(player);
    }

    public int getEarnings(Player player) {
        return earnings.get(player);
    }

    public int getPlayerProfit(Player player) {
        return earnings.get(player);
    }

    public int getDealerProfit(Dealer dealer) {
        return earnings.values().stream()
                .mapToInt(value -> value * -1)
                .sum();
    }
}

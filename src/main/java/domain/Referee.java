package domain;

import java.util.List;
import java.util.Map;

public class Referee {
    public static List<Betting> bettings;

    public static Dealer dealer;

    public Referee(Dealer dealer, Bettings bettings) {
        this.dealer = dealer;
        this.bettings = bettings.getBettings();
    }

    public void decideWinner(Player player, Map<Gambler, Integer> result) {
        if (isPlayerWin(player, dealer)) {
            result.put(player, 1);
        }

        if (isDealerWin(player, dealer)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return (dealerScore <= playerScore && !player.isBustedGambler()
                || (dealer.isBustedGambler() && !player.isBustedGambler()));
    }

    private boolean isDealerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return dealerScore > playerScore || player.isBustedGambler();
    }

    public void decideBenefits(Player player, List<Player> players, Map<Gambler, Integer> benefits) {
        int index = players.indexOf(player);
        int winnerCount = 0;

        addPlayerBenefits(player, benefits);
        addDealerBenefit(player, players, benefits, index, winnerCount);
    }

    private void addDealerBenefit(Player player, List<Player> players, Map<Gambler, Integer> benefits, int index, int winnerCount) {
        if (isDealerWin(player, dealer)) {
            loseBenefit(player, benefits, bettings.get(index).getBetting());
            winnerCount = getWinnerCount(player, players, winnerCount + 1);
            int money = bettings.get(index).getBetting() / winnerCount;
            earnBenefits(player, players, benefits, money);
        }
    }

    private void addPlayerBenefits(Player player, Map<Gambler, Integer> benefits) {
        if (isPlayerWin(player, dealer) && !benefits.containsKey(player)) {
            benefits.put(player, 0);
        }
    }

    private void earnBenefits(Player player, List<Player> players, Map<Gambler, Integer> benefits, int money) {
        earnBenefit(benefits, money, dealer);
        for (Player searchPlayer : players) {
            playerEarnBenefit(player, benefits, money, searchPlayer);
        }
    }

    private void playerEarnBenefit(Player player, Map<Gambler, Integer> benefits, int money, Player searchPlayer) {
        if (searchPlayer != player && isPlayerWin(searchPlayer, dealer)) {
            earnBenefit(benefits, money, searchPlayer);
        }
    }

    private int getWinnerCount(Player player, List<Player> players, int winnerCount) {
        for (Player searchPlayer : players) {
            winnerCount = getWinnerCount(player, winnerCount, searchPlayer);
        }
        return winnerCount;
    }

    private int getWinnerCount(Player player, int winnerCount, Player searchPlayer) {
        if (searchPlayer != player && isPlayerWin(searchPlayer, dealer)) {
            return winnerCount + 1;
        }
        return winnerCount;
    }

    private void loseBenefit(Player player, Map<Gambler, Integer> benefits, int money) {
        if (!benefits.containsKey(player)) {
            benefits.put(player, 0 - money);
            return;
        }

        if (benefits.containsKey(player)) {
            benefits.replace(player, benefits.get(player) - money);
        }
    }

    private void earnBenefit(Map<Gambler, Integer> benefits, int money, Gambler gambler) {
        if (!benefits.containsKey(gambler)) {
            benefits.put(gambler, money);
        }

        if (benefits.containsKey(gambler)) {
            benefits.replace(gambler, benefits.get(gambler) + money);
        }
    }
}

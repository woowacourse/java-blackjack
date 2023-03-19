package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    public List<Betting> bettings;
    public Dealer dealer;

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

    public Map<Gambler, Integer> calculateBenefits(Players players, Dealer dealer) {
        Map<Gambler, Integer> benefits = initBenefits(players, dealer);
        for (Player player : players.getPlayers()) {
            calculateBenefit(player, players.getPlayers(), benefits);
        }
        return benefits;
    }

    public Map<Gambler, Integer> calculateBenefit(Player player, List<Player> players, Map<Gambler, Integer> benefits) {
        winDealer(player, players, benefits);
        winPlayer(player,players,benefits);

       return benefits;
    }

    private void winDealer(Player player, List<Player> players, Map<Gambler, Integer> benefits){
        int index = players.indexOf(player);
        int winnerCount = 0;

        if (isDealerWin(player, dealer)) {
            loseBenefit(player, benefits, bettings.get(index).getBetting());
            winnerCount = getWinnerCount(player, players, winnerCount + 1);
            int money = bettings.get(index).getBetting() / winnerCount;
            earnBenefit(benefits, money, dealer);
        }
    }

    private void winPlayer(Player player, List<Player> players, Map<Gambler, Integer> benefits) {
        int index = players.indexOf(player);
        int winnerCount = 0;

        if (isPlayerWin(player, dealer)) {
            winnerCount = getWinnerCount(player, players, winnerCount + 1);
            int money = bettings.get(index).getBetting() / winnerCount;
            earnPlayersBenefits(player, players, benefits, money);
        }
    }

    private Map<Gambler, Integer> initBenefits(Players players, Dealer dealer) {
        Map<Gambler, Integer> benefits = new LinkedHashMap<>();
        benefits.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            benefits.put(player, 0);
        }
        return benefits;
    }

    private void earnPlayersBenefits(Player player, List<Player> players, Map<Gambler, Integer> benefits, int money) {
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
        benefits.replace(player, benefits.get(player) - money);
    }

    private void earnBenefit(Map<Gambler, Integer> benefits, int money, Gambler gambler) {
        benefits.replace(gambler, benefits.get(gambler) + money);
    }
}

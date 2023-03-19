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
        return dealer.isBustedGambler()
                || (dealerScore <= playerScore && !player.isBustedGambler());
    }

    private boolean isDealerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return player.isBustedGambler() || dealerScore > playerScore;
    }

    public Map<Gambler, Integer> calculateBenefits(Players players, Dealer dealer) {
        Map<Gambler, Integer> benefits = initBenefits(players, dealer);
        for (Player player : players.getPlayers()) {
            calculateBenefit(player, players.getPlayers(), benefits);
        }
        return benefits;
    }

    public Map<Gambler, Integer> calculateBenefit(Player player, List<Player> players, Map<Gambler, Integer> benefits) {
        int index = players.indexOf(player);

        if (isDealerWin(player, dealer)) {
            loseBenefit(player, benefits, bettings.get(index).getBetting());
            int winnerCount = getWinnerCount(players)+1;
            int money = bettings.get(index).getBetting() / winnerCount;
            earnBenefits(benefits, money, players, player);
        }

        return benefits;
    }

    private Map<Gambler, Integer> initBenefits(Players players, Dealer dealer) {
        Map<Gambler, Integer> benefits = new LinkedHashMap<>();
        benefits.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            benefits.put(player, 0);
        }
        return benefits;
    }

    private void EarnPlayerBenefit(Player player, Map<Gambler, Integer> benefits, int money, Player searchPlayer) {
        if (!player.equals(searchPlayer) && isPlayerWin(searchPlayer, dealer)) {
            benefits.replace(searchPlayer, benefits.get(searchPlayer) + money);

        }
    }

    private int getWinnerCount(List<Player> players) {
        int winnerCount = 0;

        for (Player searchPlayer : players) {
            winnerCount += getWinnerPlayersCount(searchPlayer);
        }

        return winnerCount;
    }

    private int getWinnerPlayersCount(Player searchPlayer) {
        if (isPlayerWin(searchPlayer, dealer)) {
            return 1;
        }

        return 0;
    }

    private void loseBenefit(Player player, Map<Gambler, Integer> benefits, int money) {
        benefits.replace(player, benefits.get(player) - money);

    }

    private void earnBenefits(Map<Gambler, Integer> benefits, int money, List<Player> players, Player player) {
        benefits.replace(dealer, benefits.get(dealer) + money);

        for (Player searchPlayer : players) {
            EarnPlayerBenefit(player, benefits, money, searchPlayer);
        }
    }
}

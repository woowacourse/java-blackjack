package blackjack.domain.result;

import java.util.*;

import blackjack.domain.player.BetMoney;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Calc {

    private static final double INIT_MONEY = 0.0;

    private final Map<Player, Double> profits;

    public Calc(Players players) {
        this.profits = init(players);
    }

    private LinkedHashMap<Player, Double> init(Players players) {
        LinkedHashMap<Player, Double> map = new LinkedHashMap<>();
        players.getPlayers().forEach(player -> map.put(player, INIT_MONEY));
        return map;
    }

    public void competeDealerWithPlayers(Players players, PlayersBetMoney playersBetMoney) {
        Map<Player, BetMoney> playersMoney = playersBetMoney.getPlayersMoney();
        Set<Player> playersMoneyKey = playersMoney.keySet();
        for (Player player : playersMoneyKey) {
            scoreResultIfGuest(playersMoney, players, player);
        }
    }

    private void scoreResultIfGuest(Map<Player, BetMoney> playerMoney, Players players, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        scoreEachGuest(playerMoney, players, guest);
    }

    private void scoreEachGuest(Map<Player, BetMoney> playerMoney, Players players, Player guest) {
        Player dealer = players.getDealer();
        BetMoney betMoney = playerMoney.get(guest);
        Match match = Match.findWinner(guest, dealer);
        scoreWin(guest, dealer, betMoney, match);
        scoreLose(guest, dealer, betMoney, match);
    }

    private void scoreWin(Player guest, Player dealer, BetMoney betMoney, Match match) {
        if (match.isMatchBlackjackWin()) {
            scoreBlackjackWin(guest, dealer, betMoney);
        }
        if (match.isMatchWin()) {
            scoreWin(guest, dealer, betMoney);
        }
    }

    private void scoreWin(Player guest, Player dealer, BetMoney betMoney) {
        profits.put(guest, profits.get(guest) + ProfitCalculator.plus(betMoney));
        profits.put(dealer, profits.get(dealer) - ProfitCalculator.plus(betMoney));
    }

    private void scoreBlackjackWin(Player guest, Player dealer, BetMoney betMoney) {
        profits.put(guest, profits.get(guest) + ProfitCalculator.plusBlackjack(betMoney));
        profits.put(dealer, profits.get(dealer) - ProfitCalculator.plusBlackjack(betMoney));
    }

    private void scoreLose(Player guest, Player dealer, BetMoney betMoney, Match match) {
        if (match.isMatchLose()) {
            scoreLose(guest, dealer, betMoney);
        }
        if (match.isMatchBlackjackLose()) {
            scoreBlackjackLose(guest, dealer, betMoney);
        }
    }

    private void scoreBlackjackLose(Player guest, Player dealer, BetMoney betMoney) {
        profits.put(guest, profits.get(guest) - ProfitCalculator.plusBlackjack(betMoney));
        profits.put(dealer, profits.get(dealer) + ProfitCalculator.plusBlackjack(betMoney));
    }

    private void scoreLose(Player guest, Player dealer, BetMoney betMoney) {
        profits.put(guest, profits.get(guest) - ProfitCalculator.plus(betMoney));
        profits.put(dealer, profits.get(dealer) + ProfitCalculator.plus(betMoney));
    }

    public Map<Player, Double> getProfits() {
        return profits;
    }
}

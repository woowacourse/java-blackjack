package blackjack.domain.result;

import java.util.*;

import blackjack.domain.player.BetMoney;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class PlayersBetMoney {

    private static final int INIT_MONEY = 0;

    private final Map<Player, BetMoney> playersMoney;

    public PlayersBetMoney(Map<Player, BetMoney> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public static PlayersBetMoney of(Players players, HashMap<String, Integer> playersBetMoney) {
        return new PlayersBetMoney(initMoney(players, playersBetMoney));
    }

    private static LinkedHashMap<Player, BetMoney> initMoney(Players players, HashMap<String, Integer> playersBetMoney) {
        LinkedHashMap<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        Player dealer = players.getDealer();

        Set<String> keys = playersBetMoney.keySet();
        for (String key : keys) {
            Player player = players.getPlayer(key);
            playersMoney.put(player, new BetMoney(playersBetMoney.get(key)));
        }
        playersMoney.put(dealer, new BetMoney(INIT_MONEY));
        return playersMoney;
    }

    public void calcDealerMoney(Players players) {
        Player dealer = players.getDealer();
        double profit = playersMoney.keySet()
                .stream()
                .mapToDouble(player -> playersMoney.get(player).getMoney())
                .sum();
        playersMoney.put(dealer, new BetMoney(-profit));
    }

    private Player findPlayer(Players players, Player guest) {
        return players.getPlayers()
                .stream()
                .filter(player -> player.equals(guest))
                .findFirst()
                .orElseThrow();
    }

    public void calcPlusMoney(Players players, Player guest) {
        playersMoney.get(findPlayer(players, guest))
                .plusMoney();
    }

    public void calcPlusBlackjackMoney(Players players, Player guest) {
        playersMoney.get(findPlayer(players, guest))
                .plusBlackjackMoney();
    }

    public void calcMinusMoney(Players players, Player guest) {
        playersMoney.get(findPlayer(players, guest))
                .minusMoney();
    }

    public void calcMinusBlackjackMoney(Players players, Player guest) {
        playersMoney.get(findPlayer(players, guest))
                .minusBlackjackMoney();
    }

    public Map<Player, BetMoney> getPlayersMoney() {
        return Collections.unmodifiableMap(playersMoney);
    }
}

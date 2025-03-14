package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public class GameRound {

    private final Dealer dealer;
    private final Map<Player, Integer> initialBettingMoney = new HashMap<>();
    private final Map<Player, Integer> finalBettingMoney = new HashMap<>();

    public GameRound(Dealer dealer) {
        this.dealer = dealer;
    }

    public static GameRound start(Dealer dealer) {
        return new GameRound(dealer);
    }

    public void betting(Player player, int money) {
        initialBettingMoney.put(player, money);
        finalBettingMoney.put(player, 0);
    }

    public boolean loseIfBust(Player player) {
/*
        if (player.isBust()) {
            finalBettingMoney.put(player, 0);
            return true;
        }
        return false;
*/
        return player.isBust();
    }

    public int getFinalBettingMoney(Player player) {
        return finalBettingMoney.get(player);
    }

    public boolean endGameIfBlackjack(Player player) {
        if (player.isBlackjack()) {
            finalBettingMoney.put(player, (int)(initialBettingMoney.get(player) * 1.5));
            return true;
        }
        return false;
    }

    public boolean endGameIfBlackjack(Dealer dealer) {
        if (dealer.isBlackjack()) {
            initialBettingMoney.keySet().stream()
                .filter(Gamer::isBlackjack)
                .forEach(player -> finalBettingMoney.put(player, initialBettingMoney.get(player)));

            return true;
        }
        return false;
    }

    public void dealerBust() {
        initialBettingMoney.keySet().stream()
            .filter(player -> !player.isBust())
            .forEach(player -> finalBettingMoney.put(player, initialBettingMoney.get(player) * 2));
    }

    public void computeResult() {
        initialBettingMoney.keySet().stream()
            .filter(player -> RoundResult.judgeResult(player, dealer) == RoundResult.WIN)
            .forEach(player -> finalBettingMoney.put(player, initialBettingMoney.get(player) * 2));
    }

    public Map<Gamer, Integer> getAllProfit() {
        Map<Gamer, Integer> result = initialBettingMoney.keySet().stream()
            .collect(Collectors.toMap(
                player -> player,
                player -> finalBettingMoney.get(player) - initialBettingMoney.get(player)));
        int playersProfitSum = result.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
        result.put(dealer, -playersProfitSum);
        return result;
    }
}

package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.judge.WinState;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cashier {

    private final List<PlayerBet> playerBets;

    public Cashier(List<PlayerBet> playerBets) {
        this.playerBets = playerBets;
    }

    public Map<Player, BetAmount> calculatePlayersProfits(Map<Player, WinState> resultWithoutProfit, Dealer dealer) {
        Map<Player, BetAmount> resultWithProfit = applyProfitByWinState(resultWithoutProfit);
        int totalProfitAmount = calculateProfitAmount(resultWithProfit);
        resultWithProfit.put(dealer, new BetAmount(-totalProfitAmount));
        return Collections.unmodifiableMap(resultWithProfit);
    }

    private Map<Player, BetAmount> applyProfitByWinState(Map<Player, WinState> resultWithoutProfit) {
        Map<Player, BetAmount> resultWithProfit = new LinkedHashMap<>();
        for (Map.Entry<Player, WinState> playerWinState : resultWithoutProfit.entrySet()) {
            Player player = playerWinState.getKey();
            WinState winState = playerWinState.getValue();
            BetAmount betAmount = findBetAmountByPlayer(player);

            resultWithProfit.put(player, calculateProfitByWinState(winState, betAmount));
        }
        return resultWithProfit;
    }

    private BetAmount findBetAmountByPlayer(Player player) {
        return playerBets.stream()
                .filter(playerBet -> playerBet.getPlayer().equals(player))
                .map(PlayerBet::getBetAmount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어의 베팅금액을 찾을 수 없습니다."));
    }

    private BetAmount calculateProfitByWinState(WinState winState, BetAmount betAmount) {
        int finalProfit = (int) (betAmount.getBetAmount() * winState.getProfitRate());
        return new BetAmount(finalProfit);
    }

    private int calculateProfitAmount(Map<Player, BetAmount> resultWithProfit) {
        return resultWithProfit.values().stream()
                .mapToInt(BetAmount::getBetAmount)
                .sum();
    }
}

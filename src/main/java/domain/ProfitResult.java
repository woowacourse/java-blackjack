package domain;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.*;
import java.util.stream.Collectors;

public class ProfitResult {
    private List<String> winningUserResult;

    public ProfitResult(Players players, Dealer dealer) {
        Map<String, Double> winningPlayerResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            winningPlayerResult.put(player.getName(), Profit.calculateProfit(player, dealer));
        }

        this.winningUserResult = generateWinningUserResult(winningPlayerResult);
    }

    private List<String> generateWinningUserResult(Map<String, Double> winningPlayerResult) {

        double dealerProfit =  winningPlayerResult.values().stream().mapToDouble(Double::doubleValue).count();

        List<String> result = new ArrayList<>(Collections.singletonList(String.format(
                "딜러: %1f", dealerProfit))
        );

        result.addAll(winningPlayerResult.keySet()
                .stream()
                .map(player-> String.format("%s: %1f",player,winningPlayerResult.get(player)))
                .collect(Collectors.toList())
        );

        return result;
    }

    public List<String> getWinningUserResult() {
        return Collections.unmodifiableList(winningUserResult);
    }
}

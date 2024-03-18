package dto;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.Profit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record GameResult(Double dealerResult, Map<String, Double> playerResult) {

    public static GameResult of(final Dealer dealer, final Players players) {

        final LinkedHashMap<String, Double> playersResult = players.getValue()
                .stream()
                .collect(Collectors.toMap(Player::getName, player -> player.calculateProfit(dealer), (a, b) -> a,
                        LinkedHashMap::new));
        final double dealerProfitAmount = Profit.reverse(players.getTotalSum(dealer));
        return new GameResult(dealerProfitAmount, playersResult);
    }
}

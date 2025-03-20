package blackjack.domain.round;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.response.ProfitsResponseDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProfitCalculator {

    private final LinkedHashMap<String, Bet> playerBets = new LinkedHashMap<>();

    public void addPlayerBet(String playerName, int amount) {
        playerBets.put(playerName, new Bet(amount));
    }

    public ProfitsResponseDto getProfits(Dealer dealer, List<Player> players) {
        Map<String, Double> playerProfits = getPlayerProfits(dealer, players);
        Map<String, Double> dealerProfit = getDealerProfit(dealer, playerProfits);
        return ProfitsResponseDto.of(dealerProfit, playerProfits);
    }

    private Map<String, Double> getPlayerProfits(Dealer dealer, List<Player> players) {
        Map<String, Double> profits = new LinkedHashMap<>();
        for (Player player : players) {
            double playerProfit = getPlayerProfit(player, dealer);
            profits.put(player.getName(), playerProfit);
        }
        return profits;
    }

    private double getPlayerProfit(Player player, Dealer dealer) {
        Bet playerBet = playerBets.get(player.getName());
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);
        return getProfit(playerBet, roundResult);
    }

    private double getProfit(Bet bet, RoundResult roundResult) {
        return bet.value() * roundResult.getProfitMultiplier();
    }

    private Map<String, Double> getDealerProfit(Dealer dealer, Map<String, Double> playerProfits) {
        double totalPlayerProfit = playerProfits.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return new LinkedHashMap<>(Map.ofEntries(
                Map.entry(dealer.getName(), -totalPlayerProfit)
        ));
    }
}

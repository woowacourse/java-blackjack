package service;

import domain.BetAmount;
import domain.Game;
import domain.enums.Result;
import dto.CardDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackService {
    public Map<String, List<CardDto>> makePlayerCardDtos(Game game) {
        Map<String, List<CardDto>> playerCards = new LinkedHashMap<>();
        for (String name : game.getAllPlayerNames()) {
            playerCards.put(name, CardDto.fromCards(game.getPlayerCards(name)));
        }
        return playerCards;
    }

    public Map<String, Result> makePlayerResults(Game game) {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : game.getAllPlayerNames()) {
            playerResults.put(name, game.getPlayerResult(name));
        }
        return playerResults;
    }

    public Map<String, Integer> calculateAllPlayerProfits(Map<String, Result> playerResults,
                                                          Map<String, BetAmount> betAmounts) {
        Map<String, Integer> profits = new LinkedHashMap<>();
        for (Entry<String, BetAmount> entry : betAmounts.entrySet()) {
            int profit = calculatePlayerProfit(playerResults.get(entry.getKey()), entry.getValue());
            profits.put(entry.getKey(), profit);
        }
        return profits;
    }

    private int calculatePlayerProfit(Result result, BetAmount betAmount) {
        double profit = betAmount.amount() * result.getRate();
        return (int) profit;
    }

    public int calculateDealerProfit(Map<String, Integer> allPlayerProfits) {
        int dealerProfit = 0;
        for (int profit : allPlayerProfits.values()) {
            dealerProfit += -profit;
        }
        return dealerProfit;
    }
}

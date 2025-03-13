package domain.participant;

import domain.blackjackgame.BlackjackGame;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetManager {

    private final String dealerName;
    private final Map<String, BlackjackBet> pariticipantsBets = new LinkedHashMap<>();

    public BetManager(List<String> names, List<Integer> bets, String dealerName) {
        this.dealerName = dealerName;
        for (int idx = 0; idx < names.size(); idx++) {
            pariticipantsBets.put(names.get(idx), new BlackjackBet(bets.get(idx)));
        }
    }

    public Map<String, Double> blackjackBettingResult(BlackjackGame blackjackGame) {
        Map<String, Double> blackjackBetResults = new LinkedHashMap<>(Map.of(dealerName, 0.0));
        BlackjackCardSum dealerSum = new BlackjackCardSum(blackjackGame.dealerCards());
        for (String name : pariticipantsBets.keySet()) {
            BlackjackCardSum playerCardSum = new BlackjackCardSum(blackjackGame.playerCards(name));
            double playerWinningMoney = playerWinningMoney(name, playerCardSum, dealerSum);
            blackjackBetResults.put(dealerName, dealerWinningMoney(blackjackBetResults, playerWinningMoney));
            blackjackBetResults.put(name, playerWinningMoney);
        }
        return blackjackBetResults;
    }

    private double dealerWinningMoney(Map<String, Double> blackjackBetResults, double playerWinningMoney) {
        return blackjackBetResults.get(dealerName) - playerWinningMoney;
    }

    public double playerWinningMoney(String name, BlackjackCardSum cardSum, BlackjackCardSum dealerSum) {
        BlackjackBet blackjackBet = pariticipantsBets.get(name);
        return blackjackBet.calculateWinningMoney(cardSum, dealerSum) - blackjackBet.betMoney();
    }
}

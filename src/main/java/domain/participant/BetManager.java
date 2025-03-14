package domain.participant;

import domain.blackjackgame.BlackjackGame;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetManager {

    private final String dealerName;
    private final Map<String, BlackjackBet> participantsBets = new LinkedHashMap<>();

    public BetManager(List<String> names, List<Integer> bets, String dealerName) {
        this.dealerName = dealerName;
        for (int idx = 0; idx < names.size(); idx++) {
            participantsBets.put(names.get(idx), new BlackjackBet(bets.get(idx)));
        }
    }

    public Map<String, Double> blackjackBettingResult(BlackjackGame blackjackGame) {
        Map<String, Double> participantsEarnMoney = new LinkedHashMap<>(Map.of(dealerName, 0.0));
        BlackjackCardSum dealerSum = new BlackjackCardSum(blackjackGame.dealerCards());
        for (String name : participantsBets.keySet()) {
            BlackjackCardSum playerCardSum = new BlackjackCardSum(blackjackGame.playerCards(name));
            double playerProfit = playerProfit(name, playerCardSum, dealerSum);
            participantsEarnMoney.put(dealerName, dealerProfit(participantsEarnMoney, playerProfit));
            participantsEarnMoney.put(name, playerProfit);
        }
        return participantsEarnMoney;
    }

    private double dealerProfit(Map<String, Double> participantsEarnMoney, double playerProfit) {
        return participantsEarnMoney.get(dealerName) - playerProfit;
    }

    public double playerProfit(String name, BlackjackCardSum cardSum, BlackjackCardSum dealerSum) {
        BlackjackBet blackjackBet = participantsBets.get(name);
        return blackjackBet.calculateEarnMoney(cardSum, dealerSum) - blackjackBet.betMoney();
    }
}

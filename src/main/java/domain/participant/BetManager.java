package domain.participant;

import domain.blackjackgame.BlackjackGame;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetManager {

    private final Map<String, BlackjackBet> participantsBets = new LinkedHashMap<>();

    public BetManager(List<String> names, List<Integer> bets) {
        for (int idx = 0; idx < names.size(); idx++) {
            participantsBets.put(names.get(idx), new BlackjackBet(bets.get(idx)));
        }
    }

    public Map<String, Double> blackjackBettingResult(BlackjackGame blackjackGame) {
        String dealerName = blackjackGame.dealerName();
        Map<String, Double> participantsEarnMoney = new LinkedHashMap<>(Map.of(dealerName, 0.0));
        BlackjackHands dealerHands = blackjackGame.dealerHands();
        for (String name : participantsBets.keySet()) {
            BlackjackHands playerHands = blackjackGame.playerHands(name);
            double playerProfit = playerProfit(name, playerHands, dealerHands);
            participantsEarnMoney.put(dealerName, dealerProfit(participantsEarnMoney, playerProfit, dealerName));
            participantsEarnMoney.put(name, playerProfit);
        }
        return participantsEarnMoney;
    }

    private double dealerProfit(Map<String, Double> participantsEarnMoney, double playerProfit, String dealerName) {
        return participantsEarnMoney.get(dealerName) - playerProfit;
    }

    public double playerProfit(String name, BlackjackHands cardSum, BlackjackHands dealerSum) {
        BlackjackBet blackjackBet = participantsBets.get(name);
        return blackjackBet.calculateEarnMoney(cardSum, dealerSum) - blackjackBet.betMoney();
    }
}

package domain.participant;

import domain.blackjackgame.BlackjackGame;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetManager {

    private final BlackjackGame blackjackGame;

    public BetManager(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

    public Map<String, Double> blackjackBettingResult() {
        String dealerName = blackjackGame.dealerName();
        Map<String, Double> participantsEarnMoney = new LinkedHashMap<>(Map.of(dealerName, 0.0));
        BlackjackHands dealerHands = blackjackGame.dealerHands();
        for (String name : blackjackGame.playerNames()) {
            BlackjackHands playerHands = blackjackGame.playerHands(name);
            double playerProfit = playerProfit(blackjackGame.playerBet(name), playerHands, dealerHands);
            participantsEarnMoney.put(dealerName, dealerProfit(participantsEarnMoney, playerProfit, dealerName));
            participantsEarnMoney.put(name, playerProfit);
        }
        return participantsEarnMoney;
    }

    private double dealerProfit(Map<String, Double> participantsEarnMoney, double playerProfit, String dealerName) {
        return participantsEarnMoney.get(dealerName) - playerProfit;
    }

    private double playerProfit(BlackjackBet playerBet, BlackjackHands cardSum, BlackjackHands dealerSum) {
        return playerBet.calculateEarnMoney(cardSum, dealerSum) - playerBet.betMoney();
    }
}

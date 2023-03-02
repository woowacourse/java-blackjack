package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class ResultCalculator {

    private final Map<String, List<Integer>> results;

    public ResultCalculator(Players players, Dealer dealer) {
        this.results = new LinkedHashMap<>();
        results.put(dealer.getName(), new ArrayList<>(List.of(0, 0, 0)));
        for (Player player : players.getPlayers()) {
            results.put(player.getName(), new ArrayList<>(List.of(0, 0, 0)));
        }
    }

    public void executeGame(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            fight(player, dealer);
        }
    }

    public void fight(Player player, Dealer dealer) {
        int playerTotalValue = checkBust(player.getMaxSum());
        int dealerTotalValue = checkBust(dealer.getMaxSum());
        List<Integer> playerResult = results.get(player.getName());
        List<Integer> dealerResult = results.get(dealer.getName());
        if (playerTotalValue > dealerTotalValue) {
            playerWin(playerResult, dealerResult);
        }
        if (playerTotalValue < dealerTotalValue) {
            dealerWin(playerResult, dealerResult);
        }
        if (playerTotalValue == dealerTotalValue) {
            draw(playerResult, dealerResult);
        }
    }

    private void playerWin(List<Integer> playerResult, List<Integer> dealerResult) {
        playerResult.set(0, playerResult.get(0) + 1);
        dealerResult.set(2, dealerResult.get(2) + 1);
    }

    private void dealerWin(List<Integer> playerResult, List<Integer> dealerResult) {
        playerResult.set(2, playerResult.get(2) + 1);
        dealerResult.set(0, dealerResult.get(0) + 1);
    }

    private void draw(List<Integer> playerResult, List<Integer> dealerResult) {
        playerResult.set(1, playerResult.get(1) + 1);
        dealerResult.set(1, dealerResult.get(1) + 1);
    }

    private int checkBust(int totalValue) {
        if (totalValue > 21) {
            totalValue = 0;
        }
        return totalValue;
    }

    public List<String> getFinalFightResults() {
        List<String> finalFightResults = new ArrayList<>();
        for (String name : results.keySet()) {
            List<Integer> dealerResult = results.get(name);
            Integer winCount = dealerResult.get(0);
            Integer drawCount = dealerResult.get(1);
            Integer loseCount = dealerResult.get(2);

            StringBuilder sb = new StringBuilder(name + ": ");
            if (winCount != 0) {
                sb.append(winCount).append("승 ");
            }
            if (drawCount != 0) {
                sb.append(drawCount).append("무 ");
            }
            if (loseCount != 0) {
                sb.append(loseCount).append("패");
            }
            finalFightResults.add(sb.toString());
        }
        return finalFightResults;
    }

    public List<Integer> getResultsByName(String name) {
        return results.get(name);
    }
}

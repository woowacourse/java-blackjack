package domain;

import java.util.*;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class ResultCalculator {

    private final Map<String, List<Integer>> results;

    public ResultCalculator(Players players, Dealer dealer) {
        this.results = new HashMap<>();
        results.put(dealer.getName(), new ArrayList<>(List.of(0, 0, 0)));
        for (Player player : players.getPlayers()) {
            results.put(player.getName(), new ArrayList<>(List.of(0, 0, 0)));
        }
    }

    public void fight(Player player ,Dealer dealer) {
        int playerTotalValue = checkBust(player.getTotalValue());
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

    public List<Integer> getResultsByName(String name) {
        return results.get(name);
    }
}

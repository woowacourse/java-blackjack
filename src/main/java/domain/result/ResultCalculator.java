package domain.result;

import static domain.participant.Participant.MIN_BUST_NUMBER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class ResultCalculator {

    private static final String WIN = "승 ";
    private static final String DRAW = "무 ";
    private static final String LOSE = "패";
    private final Map<String, Map<String, Integer>> results;

    public ResultCalculator(Players players, Dealer dealer) {
        this.results = new LinkedHashMap<>();
        results.put(dealer.getName(), initResults());
        for (Player player : players.getPlayers()) {
            results.put(player.getName(), initResults());
        }
    }

    public void executeGame(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            fight(player, dealer);
        }
    }

    public void fight(Player player, Dealer dealer) {
        int playerWinOrLose = checkBust(player.calculateScore()) - checkBust(dealer.calculateScore());
        if (playerWinOrLose > 0) {
            playerWin(results.get(player.getName()), results.get(dealer.getName()));
        }
        if (playerWinOrLose < 0) {
            dealerWin(results.get(player.getName()), results.get(dealer.getName()));
        }
        if (playerWinOrLose == 0) {
            draw(results.get(player.getName()), results.get(dealer.getName()));
        }
    }

    public List<String> getFinalFightResults() {
        List<String> finalFightResults = new ArrayList<>();
        for (String name : results.keySet()) {
            List<Integer> participantResult = getResultsByName(name);
            StringBuilder sb = getResultCount(name, participantResult);
            finalFightResults.add(sb.toString());
        }
        return finalFightResults;
    }

    public List<Integer> getResultsByName(String name) {
        Map<String, Integer> resultsMapName = results.get(name);
        ArrayList<Integer> results = new ArrayList<>();
        results.add(resultsMapName.get(WIN));
        results.add(resultsMapName.get(DRAW));
        results.add(resultsMapName.get(LOSE));

        return results;
    }

    private int checkBust(int maxSum) {
        if (maxSum > MIN_BUST_NUMBER) {
            return 0;
        }
        return maxSum;
    }

    private void playerWin(Map<String, Integer> playerResult, Map<String, Integer> dealerResult) {
        playerResult.replace(WIN, playerResult.get(WIN) + 1);
        dealerResult.replace(LOSE, dealerResult.get(LOSE) + 1);
    }

    private void dealerWin(Map<String, Integer> playerResult, Map<String, Integer> dealerResult) {
        playerResult.replace(LOSE, playerResult.get(LOSE) + 1);
        dealerResult.replace(WIN, dealerResult.get(WIN) + 1);
    }

    private void draw(Map<String, Integer> playerResult, Map<String, Integer> dealerResult) {
        playerResult.replace(DRAW, playerResult.get(DRAW) + 1);
        dealerResult.replace(DRAW, dealerResult.get(DRAW) + 1);
    }

    private Map<String, Integer> initResults() {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put(WIN, 0);
        resultMap.put(DRAW, 0);
        resultMap.put(LOSE, 0);
        return resultMap;
    }

    private StringBuilder getResultCount(String name, List<Integer> participantResult) {
        StringBuilder sb = new StringBuilder(name + ": ");
        if (participantResult.get(0) != 0) {
            sb.append(participantResult.get(0)).append(WIN);
        }
        if (participantResult.get(1) != 0) {
            sb.append(participantResult.get(1)).append(DRAW);
        }
        if (participantResult.get(2) != 0) {
            sb.append(participantResult.get(2)).append(LOSE);
        }
        return sb;
    }
}

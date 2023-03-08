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
    private final Map<String, Map<Score, Integer>> results;

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
        Map<Score, Integer> resultsMapName = results.get(name);
        ArrayList<Integer> results = new ArrayList<>();
        results.add(resultsMapName.get(Score.WIN));
        results.add(resultsMapName.get(Score.DRAW));
        results.add(resultsMapName.get(Score.LOSE));

        return results;
    }

    private int checkBust(int maxSum) {
        if (maxSum > MIN_BUST_NUMBER) {
            return 0;
        }
        return maxSum;
    }

    private void playerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.replace(Score.WIN, playerResult.get(Score.WIN) + 1);
        dealerResult.replace(Score.LOSE, dealerResult.get(Score.LOSE) + 1);
    }

    private void dealerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.replace(Score.LOSE, playerResult.get(Score.LOSE) + 1);
        dealerResult.replace(Score.WIN, dealerResult.get(Score.WIN) + 1);
    }

    private void draw(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.replace(Score.DRAW, playerResult.get(Score.DRAW) + 1);
        dealerResult.replace(Score.DRAW, dealerResult.get(Score.DRAW) + 1);
    }

    private Map<Score, Integer> initResults() {
        Map<Score, Integer> resultMap = new HashMap<>();
        resultMap.put(Score.WIN, 0);
        resultMap.put(Score.DRAW, 0);
        resultMap.put(Score.LOSE, 0);
        return resultMap;
    }

    private StringBuilder getResultCount(String name, List<Integer> participantResult) {
        StringBuilder sb = new StringBuilder(name + ": ");
        if (participantResult.get(0) != 0) {
            sb.append(participantResult.get(0)).append(Score.WIN.getValue());
        }
        if (participantResult.get(1) != 0) {
            sb.append(participantResult.get(1)).append(Score.DRAW.getValue());
        }
        if (participantResult.get(2) != 0) {
            sb.append(participantResult.get(2)).append(Score.LOSE.getValue());
        }
        return sb;
    }
}

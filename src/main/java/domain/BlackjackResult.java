package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackResult {

    private final Map<Player, WinOrLose> playerResult = new LinkedHashMap<>();

    private BlackjackResult(Dealer dealer, Players players) {
        compareResult(dealer, players);
    }

    public static BlackjackResult of(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    public void compareResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            int playerScore = player.getScoreOrZeroIfBust();
            int dealerScore = dealer.getScoreOrZeroIfBust();

            playerResult.put(player, getJudgeResult(playerScore, dealerScore));
        }
    }

    private WinOrLose getJudgeResult(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WinOrLose.WIN;
        }
        if (playerScore < dealerScore) {
            return WinOrLose.LOSE;
        }
        return WinOrLose.DRAW;

    }

    public List<String> getPlayersResult() {
        List<String> results = new ArrayList<>();

        for (Entry<Player, WinOrLose> playerWinOrLoseEntry : playerResult.entrySet()) {
            Player player = playerWinOrLoseEntry.getKey();
            String winningResult = playerWinOrLoseEntry.getValue().getMessage();

            results.add(player.getName() + ": " + winningResult);
        }

        return results;
    }

    public String getDealerResult() {
        return "딜러: " + countPlayerResult(WinOrLose.LOSE) + "승 " + countPlayerResult(WinOrLose.DRAW) + "무 "
                + countPlayerResult(WinOrLose.WIN) + "패";
    }

    private int countPlayerResult(WinOrLose winOrLose) {
        return (int) playerResult.values()
                .stream()
                .filter(value -> value == winOrLose)
                .count();
    }

}

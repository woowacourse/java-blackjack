package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            getJudgeResult(dealer, player);
        }
    }

    private void getJudgeResult(Dealer dealer, Player player) {
        if (player.isBust(player.calculateScore())) {
            playerResult.put(player, WinOrLose.LOSE);
            return;
        }
        if (dealer.isBust(dealer.calculateScore())) {
            playerResult.put(player, WinOrLose.WIN);
            return;
        }
        if (dealer.calculateScore() < player.calculateScore()) {
            playerResult.put(player, WinOrLose.WIN);
            return;
        }
        if (dealer.calculateScore() > player.calculateScore()) {
            playerResult.put(player, WinOrLose.LOSE);
            return;
        }
        playerResult.put(player, WinOrLose.DRAW);
    }

    public int countDealerWinOrLoseReversePlayerResult(WinOrLose winOrLose) {
        return (int) playerResult.values()
                .stream()
                .filter(value -> value == winOrLose)
                .count();
    }

    public List<WinOrLose> getPlayersResult() {
        return playerResult.values().stream().toList();
    }

}

package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackResult {

    private final Map<Player, GameResult> playerResult = new LinkedHashMap<>();

    private BlackjackResult(Dealer dealer, Players players) {
        compareResult(dealer, players);
    }

    public static BlackjackResult of(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    private void compareResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            getJudgeResult(dealer, player);
        }
    }

    private void getJudgeResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            playerResult.put(player, GameResult.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playerResult.put(player, GameResult.WIN);
            return;
        }
        if (dealer.calculateScore() < player.calculateScore()) {
            playerResult.put(player, GameResult.WIN);
            return;
        }
        if (dealer.calculateScore() > player.calculateScore()) {
            playerResult.put(player, GameResult.LOSE);
            return;
        }
        playerResult.put(player, GameResult.DRAW);
    }

    public int countDealerWinOrLoseReversePlayerResult(GameResult winOrLose) {
        return (int) playerResult.values()
                .stream()
                .filter(value -> value == winOrLose)
                .count();
    }

    public List<GameResult> getPlayersResult() {
        return playerResult.values().stream().toList();
    }

}

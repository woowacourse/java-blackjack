package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {

    private final Map<Player, GameResult> playerResult = new LinkedHashMap<>();

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

            playerResult.put(player, judgeResult(playerScore, dealerScore));
        }
    }

    private GameResult judgeResult(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;

    }

    public DealerResultDto getDealerResult() {
        return DealerResultDto.of(
                countPlayerResult(GameResult.LOSE),
                countPlayerResult(GameResult.DRAW),
                countPlayerResult(GameResult.WIN)
        );
    }

    private int countPlayerResult(GameResult gameResult) {
        return (int) playerResult.values()
                .stream()
                .filter(value -> value == gameResult)
                .count();
    }

    public Map<Player, GameResult> getPlayerResults() {
        return playerResult;
    }
}

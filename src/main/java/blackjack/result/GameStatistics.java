package blackjack.result;

import blackjack.participant.GameParticipants;
import blackjack.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, GameResult> playerToResult;

    private GameStatistics() {
        this.playerToResult = new LinkedHashMap<>();
    }

    public static GameStatistics from(GameParticipants participants) {
        GameStatistics gameStatistics = new GameStatistics();

        participants.getPlayers().forEach(player ->
                gameStatistics.markResult(player, player.judgeResult(participants.getDealer())));

        return gameStatistics;
    }

    public Money getProfit(Player player) {
        return player.calcProfit(playerToResult.get(player));
    }

    public Money getDealerProfit() {
        return Money.sumOf(
                playerToResult.keySet().stream()
                        .map(this::getProfit)
                        .toList());
    }

    private void markResult(Player player, GameResult result) {
        playerToResult.put(player, result);
    }
}

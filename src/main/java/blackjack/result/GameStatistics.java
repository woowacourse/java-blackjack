package blackjack.result;

import blackjack.participant.GameParticipants;
import blackjack.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameStatistics {

    private final Map<Player, GameResult> playerToResult;

    private GameStatistics(Map<Player, GameResult> playerToResult) {
        this.playerToResult = playerToResult;
    }

    public static GameStatistics from(GameParticipants participants) {
        Map<Player, GameResult> results = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            results.put(player, player.judgeResult(participants.getDealer()));
        }

        return new GameStatistics(results);
    }

    public Money getProfit(Player player) {
        return player.calculateProfit(playerToResult.get(player));
    }

    public Money getDealerProfit() {
        return Money.sumOf(
                playerToResult.keySet().stream()
                        .map(this::getProfit)
                        .toList());
    }
}

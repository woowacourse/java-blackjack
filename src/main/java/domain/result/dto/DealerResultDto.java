package domain.result.dto;

import domain.gamer.Player;
import domain.result.GameResults;
import domain.result.PlayerResult;

import java.util.Map;
import java.util.stream.Collectors;

public class DealerResultDto {
    private int winCount;
    private int drawCount;
    private int loseCount;

    private DealerResultDto(int winCount, int drawCount, int loseCount) {
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public static DealerResultDto of(GameResults gameResults) {
        Map<Player, PlayerResult> results = gameResults.getGameResults();
        Map<PlayerResult, Integer> countResult = results.keySet().stream()
                .collect(Collectors.groupingBy(results::get, Collectors.reducing(0, i -> 1, Integer::sum)));
        return new DealerResultDto(countResult.getOrDefault(PlayerResult.LOSE, 0),
                countResult.getOrDefault(PlayerResult.DRAW, 0),
                countResult.getOrDefault(PlayerResult.WIN, 0));
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}

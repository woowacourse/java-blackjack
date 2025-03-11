package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.game.GameResult;
import domain.game.GameStatistics;
import domain.participants.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatisticsTest {
    @Test
    @DisplayName("딜러의 게임 결과를 정확히 반환하는지 테스트")
    void getDealerWinCount() {
        // given
        Map<PlayerName, GameResult> result = Map.of(
                new PlayerName("a"), GameResult.WIN,
                new PlayerName("b"), GameResult.DRAW,
                new PlayerName("c"), GameResult.LOSE,
                new PlayerName("d"), GameResult.WIN,
                new PlayerName("e"), GameResult.DRAW,
                new PlayerName("f"), GameResult.DRAW);
        GameStatistics gameStatistics = new GameStatistics(result);
        // when
        int winCount = gameStatistics.getDealerWinCount();
        int loseCount = gameStatistics.getDealerLoseCount();
        int drawCount = gameStatistics.getDealerDrawCount();
        assertAll(
                () -> assertEquals(1, winCount),
                () -> assertEquals(2, loseCount),
                () -> assertEquals(3, drawCount)
        );
    }
}
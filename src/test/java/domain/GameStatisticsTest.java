package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatisticsTest {
    @Test
    @DisplayName("딜러의 게임 결과를 정확히 반환하는지 테스트")
    void getDealerWinCount() {
        // given
        Map<String, GameResult> result = Map.of("a", GameResult.WIN,
                "b", GameResult.DRAW,
                "c", GameResult.LOSE,
                "d", GameResult.WIN,
                "e", GameResult.DRAW,
                "f", GameResult.DRAW);
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
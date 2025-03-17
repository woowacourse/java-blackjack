package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static testFixture.PlayerNameFixture.*;

import domain.participants.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testFixture.PlayerNameFixture;

class GameStatisticsTest {

    @BeforeAll
    public static void setUp(){
        PlayerNameFixture.setUp();
    }

    @Test
    @DisplayName("딜러의 게임 결과를 정확히 반환하는지 테스트")
    void getDealerWinCount() {
        // given
        Map<PlayerName, GameResult> result = Map.of(
                playerNameA, GameResult.WIN,
                playerNameB, GameResult.DRAW,
                playerNameC, GameResult.LOSE,
                playerNameD, GameResult.WIN,
                playerNameE, GameResult.DRAW,
                playerNameF, GameResult.DRAW);
        GameStatistics gameStatistics = new GameStatistics(result);
        // when
        int winCount = gameStatistics.getDealerWinCount();
        int loseCount = gameStatistics.getDealerLoseCount();
        int drawCount = gameStatistics.getDealerDrawCount();
        // then
        assertThat(winCount).isEqualTo(1);
        assertThat(loseCount).isEqualTo(2);
        assertThat(drawCount).isEqualTo(3);
    }
}
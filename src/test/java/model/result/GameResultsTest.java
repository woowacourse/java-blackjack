package model.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultsTest {

    @DisplayName("플레이어의 이름을 통해 플레이어의 결과를 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "pobi, WIN",
            "jason, WIN",
            "danny, DRAW",
            "hotteok, LOSE"
    })
    void playerResultTest(final String name, final GameResult gameResult) {
        GameResults gameResults = new GameResults(Map.of(
                "pobi", GameResult.WIN,
                "jason", GameResult.WIN,
                "danny", GameResult.DRAW,
                "hotteok", GameResult.LOSE
        ));

        assertThat(gameResults.getGameResultByName(name)).isEqualTo(gameResult);
    }

    @DisplayName("플레이어의 게임 결과를 통해 딜러의 승,무,패 횟수를 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, 1",
            "DRAW, 1",
            "LOSE, 2"
    })
    void dealerResultCountTest(final GameResult gameResult, final int count) {
        GameResults gameResults = new GameResults(Map.of(
                "pobi", GameResult.WIN,
                "jason", GameResult.WIN,
                "danny", GameResult.DRAW,
                "hotteok", GameResult.LOSE
        ));

        assertThat(gameResults.calculateDealerResultCount(gameResult)).isEqualTo(count);
    }
}

package domain.game;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {
    @Test
    @DisplayName("정반대의 결과를 반환한다.")
    void reverseTest() {
        List<GameResult> gameResults = List.of(WIN, DRAW, LOSE);
        List<GameResult> reverseGameResult = gameResults.stream()
                .map(GameResult::reverse)
                .toList();

        assertThat(reverseGameResult).isEqualTo(List.of(LOSE, DRAW, WIN));
    }

    @ParameterizedTest()
    @CsvSource(value = {"1,LOSE", "2,DRAW", "3,WIN"})
    @DisplayName("두 결과를 비교하여 승패를 판단한다.")
    void compareTest(int current, GameResult gameResult) {
        int opponentValue = 2;

        assertThat(GameResult.compare(current, opponentValue)).isEqualTo(gameResult);
    }
}

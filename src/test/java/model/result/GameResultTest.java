package model.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @DisplayName("입력과 반대되는 GameResult를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN",
            "DRAW, DRAW"
    })
    void oppositeGameResultTest(final GameResult input, final GameResult expected) {
        assertThat(GameResult.getOppositeResult(input)).isEqualTo(expected);
    }
}

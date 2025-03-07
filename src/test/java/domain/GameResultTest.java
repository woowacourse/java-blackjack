package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {
    @DisplayName("승리를 패배로, 패배를 승리로 바꾼다")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN"
    })
    void test(GameResult origin, GameResult expected) {
        //when
        GameResult actual = origin.getReverse();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}

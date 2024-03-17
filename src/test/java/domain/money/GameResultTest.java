package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {
    @ParameterizedTest(name = "내가 1000이고 상대가 {0}이면, {1}이다.")
    @CsvSource(value = {"999,WIN", "1000,DRAW", "1001,LOSE"})
    @DisplayName("두 값을 비교하여 게임 결과를 반환한다.")
    void compareTest(int opponent, GameResult gameResult) {
        assertThat(GameResult.compare(1000, opponent)).isEqualTo(gameResult);
    }

    @ParameterizedTest(name = "1000 입력 시, 게임 결과가 {0}이면 결과값은 {1}이다.")
    @CsvSource(value = {"WIN,1000", "DRAW,0", "LOSE,-1000"})
    @DisplayName("게임 결과에 따라 다른 값을 반환한다.")
    void timesBenefitRatioTest(GameResult gameResult, int resultValue) {
        assertThat(gameResult.timesBenefitRatio(1000)).isEqualTo(resultValue);
    }
}

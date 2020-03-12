package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningResultTest {

    @DisplayName("패 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"17, 16", "15, 8", "20, 16"})
    void calculateLose(int dealerScore, int playerScore) {
        Assertions.assertThat(WinningResult.calculate(dealerScore, playerScore))
            .isEqualTo(WinningResult.LOSE);
    }

    @DisplayName("무 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"17, 17", "15, 15", "20, 20"})
    void calculateDraw(int dealerScore, int playerScore) {
        Assertions.assertThat(WinningResult.calculate(dealerScore, playerScore))
            .isEqualTo(WinningResult.DRAW);
    }

    @DisplayName("승 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"16, 17", "8, 15", "16, 20"})
    void calculate(int dealerScore, int playerScore) {
        Assertions.assertThat(WinningResult.calculate(dealerScore, playerScore))
            .isEqualTo(WinningResult.WIN);
    }
}

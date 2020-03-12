package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OutcomeTest {

    @DisplayName("패 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"17, 16", "15, 8", "20, 16"})
    void calculateLose(int dealerScore, int playerScore) {
        assertThat(Outcome.calculate(dealerScore, playerScore)).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("무 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"17, 17", "15, 15", "20, 20"})
    void calculateDraw(int dealerScore, int playerScore) {
        assertThat(Outcome.calculate(dealerScore, playerScore)).isEqualTo(Outcome.DRAW);
    }

    @DisplayName("승 산정 테스트")
    @ParameterizedTest
    @CsvSource(value = {"16, 17", "8, 15", "16, 20"})
    void calculate(int dealerScore, int playerScore) {
        assertThat(Outcome.calculate(dealerScore, playerScore)).isEqualTo(Outcome.WIN);
    }
}

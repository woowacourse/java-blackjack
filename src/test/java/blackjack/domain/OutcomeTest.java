package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OutcomeTest {

    @DisplayName("승 산정")
    @ParameterizedTest
    @CsvSource(value = {"17, 16", "15, 8", "20, 16"})
    void calculateWin(int playerScore, int dealerScore) {
        assertThat(Outcome.from(playerScore, dealerScore)).isEqualTo(Outcome.PLAYER_WIN);
    }

    @DisplayName("무 산정")
    @ParameterizedTest
    @CsvSource(value = {"17, 17", "15, 15", "20, 20"})
    void calculateDraw(int playerScore, int dealerScore) {
        assertThat(Outcome.from(playerScore, dealerScore)).isEqualTo(Outcome.PLAYER_DRAW);
    }

    @DisplayName("패 산정")
    @ParameterizedTest
    @CsvSource(value = {"16, 17", "8, 15", "16, 20", "0, 0"})
    void calculateLose(int playerScore, int dealerScore) {
        assertThat(Outcome.from(playerScore, dealerScore)).isEqualTo(Outcome.PLAYER_LOSE);
    }
}

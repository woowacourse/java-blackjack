package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameOutcomeTest {

    @ParameterizedTest
    @DisplayName("두 수를 받아 첫번째 수를 기준으로 값을 비교해 크다면 WIN, 작다면 LOST, 똑같다면 DRAW를 반환한다.")
    @CsvSource({"10, 5, WIN", "5, 10, LOSE", "10, 10, DRAW"})
    void calculateOutcome(int firstScore, int secondScore, GameOutcome expected) {
        assertThat(GameOutcome.calculateOutcome(firstScore, secondScore)).isEqualTo(expected);
    }
}

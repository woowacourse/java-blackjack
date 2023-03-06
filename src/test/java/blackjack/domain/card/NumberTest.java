package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Number;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자를 점수로 변환")
    void convertNumberToScore() {
        // expect
        Assertions.assertAll(
                () -> assertThat(Number.getScoreOf(Number.TWO)).isEqualTo(2),
                () -> assertThat(Number.getScoreOf(Number.J)).isEqualTo(10),
                () -> assertThat(Number.getScoreOf(Number.Q)).isEqualTo(10),
                () -> assertThat(Number.getScoreOf(Number.K)).isEqualTo(10)
        );
     }
}

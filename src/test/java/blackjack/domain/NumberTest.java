package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자를 점수로 변환")
    void convertNumberToScore() {
        // expect
        Assertions.assertAll(
                () -> assertThat(Number.convertNumberToScore(Number.TWO)).isEqualTo(2),
                () -> assertThat(Number.convertNumberToScore(Number.J)).isEqualTo(10),
                () -> assertThat(Number.convertNumberToScore(Number.Q)).isEqualTo(10),
                () -> assertThat(Number.convertNumberToScore(Number.K)).isEqualTo(10)
        );
     }
}

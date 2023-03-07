package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자를 점수로 변환")
    void score() {
        // expect
        Assertions.assertAll(
                () -> assertThat(Number.scoreOf(Number.TWO)).isEqualTo(2),
                () -> assertThat(Number.scoreOf(Number.J)).isEqualTo(10),
                () -> assertThat(Number.scoreOf(Number.Q)).isEqualTo(10),
                () -> assertThat(Number.scoreOf(Number.K)).isEqualTo(10)
        );
     }
}

package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자를 점수로 변환")
    void convertNumberToScore() {
        Number number1 = Number.TWO;
        Number number2 = Number.J;
        Number number3 = Number.Q;
        Number number4 = Number.K;

        // expect
        Assertions.assertAll(
                () -> assertThat(number1.convertNumberToBlackjackScore()).isEqualTo(2),
                () -> assertThat(number2.convertNumberToBlackjackScore()).isEqualTo(10),
                () -> assertThat(number3.convertNumberToBlackjackScore()).isEqualTo(10),
                () -> assertThat(number4.convertNumberToBlackjackScore()).isEqualTo(10)
        );
     }
}

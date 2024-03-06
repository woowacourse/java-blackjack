package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DenominationTest {

    @ParameterizedTest
    @CsvSource(value = {"20,1", "10,11"})
    @DisplayName("주어진 점수에 따라 최적의 Ace 값을 반환한다")
    void aceDetermine(final int score, final int expected) {
        Assertions.assertThat(Denomination.ACE.getValue(score)).isEqualTo(expected);
    }

    @Test
    @DisplayName("끗수에맞는 값을 반환한다")
    void denomination() {
        assertAll(
                () -> Assertions.assertThat(Denomination.FIVE.getValue(0)).isEqualTo(5),
                () -> Assertions.assertThat(Denomination.JACK.getValue(0)).isEqualTo(10),
                () -> Assertions.assertThat(Denomination.QUEEN.getValue(0)).isEqualTo(10),
                () -> Assertions.assertThat(Denomination.KING.getValue(0)).isEqualTo(10)
        );
    }


}

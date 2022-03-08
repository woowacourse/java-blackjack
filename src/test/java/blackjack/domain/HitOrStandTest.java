package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.HitOrStand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class HitOrStandTest {

    @ParameterizedTest
    @DisplayName("올바른 입력값이 들어온 경우 확인")
    @CsvSource(value = {
            "y, true",
            "n, false"
    })
    void inputTest(String input, boolean value) {
        HitOrStand hitOrStand = HitOrStand.valueOf(input);

        assertThat(hitOrStand.isValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @DisplayName("y 또는 n이 입력되었는지 확인")
    @ValueSource(strings = {"a", "b"})
    void inputErrorTest(String input) {
        assertThatThrownBy(() -> HitOrStand.valueOf(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예는 y, 아니오는 n을 입력해 주세요.");
    }
}

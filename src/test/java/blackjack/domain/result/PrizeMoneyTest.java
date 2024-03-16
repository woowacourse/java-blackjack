package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeMoneyTest {
    @Test
    @DisplayName("숫자를 통해 결과 금액을 생성한다.")
    void create_with_int() {
        final int value = 1000;

        final var sut = new PrizeMoney(value);

        assertThat(sut.value()).isEqualTo(value);
    }

    @Test
    @DisplayName("소수점을 통해 결과 금액을 생성한다.")
    void create_with_double() {
        final double value = 12500.75;

        final var sut = new PrizeMoney(value);

        assertThat(sut.value()).isEqualTo(12500);
    }
}

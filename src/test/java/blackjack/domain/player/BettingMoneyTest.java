package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class BettingMoneyTest {
    @Test
    @DisplayName("숫자를 통해 배팅 금액을 생성한다.")
    void some() {
        final int value = 10000;

        assertThatCode(() -> {
            final var sut = new BettingMoney(value);
            assertThat(sut.value()).isEqualTo(value);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("배팅 금액은 최소한 1보다 커야 한다.")
    void betting_money_must_greater_than_1(final int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

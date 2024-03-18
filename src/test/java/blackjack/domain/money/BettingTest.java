package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingTest {
    @DisplayName("배팅 금액이 0 이하라면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(longs = {0L, -1L})
    void minAmountExceptionTest(long amount) {
        assertThatThrownBy(() -> new Betting(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0보다 커야합니다.");
    }

    @DisplayName("배팅 금액이 0 보다 크면 예외를 발생시키지 않는다.")
    @Test
    void minAmountSuccessTest() {
        assertThatCode(() -> new Betting(1L))
                .doesNotThrowAnyException();
    }
}

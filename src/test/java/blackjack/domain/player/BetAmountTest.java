package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("배팅 금액은 양수이다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void testCreateNegative(String value) {
        // when & then
        assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 소수가 아니다.")
    @Test
    void testCreateDecimal() {
        // when & then
        assertThatThrownBy(() -> new BetAmount("1000.00000000000001"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 10 단위이다.")
    @Test
    void testCreateNotInUnits() {
        // when & then
        assertThatThrownBy(() -> new BetAmount("1001"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액을 생성한다.")
    @Test
    void testCreate() {
        // when & then
        assertThatCode(() -> new BetAmount("1000"))
                .doesNotThrowAnyException();
    }
}

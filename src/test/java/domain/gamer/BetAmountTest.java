package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("베팅 금액은 최소 만원부터 최대 50억까지 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"10000", "555000", "5000000000"})
    void betAmountMustBeBetweenTenThousandAndFiveBillion(final String betAmount) {
        // given
        // when

        // then
        assertThatCode(() -> BetAmount.from(betAmount))
                .doesNotThrowAnyException();
    }

    @DisplayName("베팅 금액이 만원 ~ 50억이 아니라면 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"9009", "5000000001"})
    void invalidBetAmount(final String betAmount) {
        // given
        // when

        // then
        assertThatThrownBy(() -> BetAmount.from(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

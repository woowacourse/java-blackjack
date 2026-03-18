package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {

    @DisplayName("베팅 금액이 숫자일 경우 정상 반환을 확인한다.")
    @Test
    void betNumberAmount() {
        // given
        Amount amount = Amount.from("10000");

        // when & then
        assertThat(amount.getValue()).isEqualTo(10000);
    }

    @DisplayName("베팅 금액이 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void betNotNumberAmount() {
        // given
        String amount = "boye";

        // when & then
        assertThatThrownBy(() -> Amount.from(amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 숫자가 입력되어야 합니다.");
    }

    @DisplayName("베팅 금액이 정수 이상인 숫자가 들어올 경우에도 정상 반환된다.")
    @Test
    void betOverIntegerAmount() {
        // given
        String rawAmount = "2200000000";

        // when
        Amount amount = Amount.from(rawAmount);

        // then
        assertThat(amount.getValue()).isEqualTo(2200000000L);
    }

    @DisplayName("베팅 금액이 0 이하일 경우 예외가 발생한다.")
    @Test
    void betZeroLessAmount() {
        // given
        String rawAmount = "-1000";

        // when & then
        assertThatThrownBy(() -> Amount.from(rawAmount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("베팅 금액은 0보다 큰 양수여야 합니다.");
    }

    @DisplayName("베팅 금액이 0일 경우 예외가 발생한다.")
    @Test
    void betZeroAmount() {
        // given
        String rawAmount = "0";

        // when & then
        assertThatThrownBy(() -> Amount.from(rawAmount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("베팅 금액은 0보다 큰 양수여야 합니다.");
    }
}

package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("양수 짝수 금액이면 정상 생성된다")
    void constructor_success_whenAmountIsPositiveEven() {
        // given
        final int amount = 1000;

        // when
        final BettingMoney bettingMoney = new BettingMoney(amount);

        // then
        assertThat(bettingMoney.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("0 이하 금액이면 예외가 발생한다")
    void constructor_throwsException_whenAmountIsZero() {
        // given & when & then
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("음수 금액이면 예외가 발생한다")
    void constructor_throwsException_whenAmountIsNegative() {
        // given & when & then
        assertThatThrownBy(() -> new BettingMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("홀수 금액이면 예외가 발생한다")
    void constructor_throwsException_whenAmountIsOdd() {
        // given & when & then
        assertThatThrownBy(() -> new BettingMoney(10001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 짝수여야 합니다.");
    }
}

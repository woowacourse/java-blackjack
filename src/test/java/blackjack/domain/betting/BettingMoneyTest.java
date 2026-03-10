package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("양수 금액으로 생성에 성공한다")
    void constructor_succeeds_whenAmountIsPositive() {
        // given & when
        BettingMoney bettingMoney = new BettingMoney(10000);

        // then
        assertThat(bettingMoney.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("0원이면 예외가 발생한다")
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
}

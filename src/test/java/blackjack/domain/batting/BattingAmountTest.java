package blackjack.domain.batting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BattingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 1_000_000})
    void 플레이어의_배팅_금액을_알_수_있다(int money) {
        final BattingAmount battingAmount = new BattingAmount(money);
        assertThat(battingAmount).isEqualTo(new BattingAmount(money));
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 1_000_001})
    void 배팅_금액이_1000원보다_작거나_1000000원보다_크다면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BattingAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 1,000 ~ 1,000,000원 사이어야 합니다.");
    }
}

package blackjack.domain.participant;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BattingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 1_000_000})
    void 플레이어의_배팅_금액을_알_수_있다(int money) {
        final BattingAmount battingAmount = new BattingAmount(money);
        assertThat(battingAmount).isEqualTo(new BattingAmount(money));
    }

    @ParameterizedTest
    @ValueSource(ints = {9_999, 1_000_001})
    void 배팅_금액이_10000원보다_작거나_1000000원보다_크다면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BattingAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 10,000 ~ 1,000,000원 사이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {11_001, 11_010, 11_100})
    void 배팅_금액이_1_000원_단위가_아니라면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new BattingAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 1,000원 단위어야 합니다.");
    }
}

package blackJack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingMoneyTest {

    @Test
    @DisplayName("배팅금액 생성 테스틑")
    void createMoney() {
        assertThat(Bet.ZERO).isNotNull();
    }

    @Test
    @DisplayName("배팅금액이 음수인 경우 테스트")
    void createInvalidMoney() {
        assertThatThrownBy(() -> Bet.ZERO.add(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("배팅금액 덧셈 테스트")
    void plusBattingMoney() {
        assertThat(Bet.ZERO.add(1000)).isEqualTo(new Bet(1000));
    }
}
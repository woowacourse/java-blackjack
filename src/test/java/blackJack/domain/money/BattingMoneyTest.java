package blackJack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingMoneyTest {

    @Test
    @DisplayName("배팅금액을 성공적으로 생성할 경우 null 값을 반환하지 않는다.")
    void createMoney() {
        assertThat(Bet.ZERO).isNotNull();
    }

    @Test
    @DisplayName("배팅금액이 음수인 경우 예외를 발생시킨다.")
    void createInvalidMoney() {
        assertThatThrownBy(() -> Bet.ZERO.add(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("배팅금액은 금액을 입력받아 현재 가지고 있는 금액에 더할 수 있다.")
    void plusBattingMoney() {
        assertThat(Bet.ZERO.add(1000)).isEqualTo(new Bet(1000));
    }
}
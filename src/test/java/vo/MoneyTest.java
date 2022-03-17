package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("Money 객체 생성 테스트")
    void createMoney() {
        // given
        int expected = 1000;
        Money money = Money.from(expected);

        // when
        int actual = money.getMoney();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("음수로 Money를 생성할 수 없다")
    void creatingMoneyWithMinusShouldFail() {
        assertThatThrownBy(() -> {
            Money money = Money.from(-1000);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅액을 1원 이상으로 입력해주세요.");
    }
}

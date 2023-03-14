package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @DisplayName("베팅 금액이 0 이상이면서 1000 단위면 생성에 성공한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 15000})
    void createTest(int money) {
        BettingMoney bettingMoney = new BettingMoney(money);
        assertThat(bettingMoney.getValue()).isEqualTo(money);
    }

    @DisplayName("베팅 금액이 0보다 작으면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -25000, -120000})
    void invalidMoneyTestWithUnderZero(int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BettingMoney.BOUNDARY_ERROR_MESSAGE);
    }

    @DisplayName("베팅 금액이 1000 단위가 아니면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 500, 15002})
    void invalidMoneyTestWithWrongUnit(int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BettingMoney.UNIT_ERROR_MESSAGE);
    }
}

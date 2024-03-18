package domain.participant;

import domain.vo.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {
    @DisplayName("배팅 금액이 음수이거나 0일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -5000})
    void bettingMoney(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수로 구성되어야 합니다.");
    }
}

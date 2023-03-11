package domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @DisplayName("1000원 단위가 아닌 입력값이 들어오면 예외처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {1500, 1100})
    void notDividedByUnit(int value) {
        assertThatThrownBy(() -> BettingAmount.valueOf(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("베팅 금액은")
            .hasMessageContaining("원 단위여야 합니다.");
    }

    @DisplayName("1000원 이하의 입력값이 들어오면 예외처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    void under1000Exception(int value) {
        assertThatThrownBy(() -> BettingAmount.valueOf(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("베팅 금액은")
            .hasMessageContaining("원 이상이어야 합니다.");
    }

    @DisplayName("베팅금액을 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 10000})
    void createBettingAmount(int value) {
        BettingAmount bettingAmount = BettingAmount.valueOf(value);
        assertThat(bettingAmount.getValue()).isEqualTo(value);
    }
}

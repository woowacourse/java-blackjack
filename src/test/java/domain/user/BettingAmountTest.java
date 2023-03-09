package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.money.BettingAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingAmountTest {

    @DisplayName("배팅 금액이 1000 미만의 정수일 때 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -100, 999, 100})
    void bettingAmount_notLessThan1000(int bettingAmount) {
        assertThatThrownBy(() -> BettingAmount.valueOf(bettingAmount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("베팅 금액은 ")
            .hasMessageContaining("원 이상이어야 합니다.");
    }

    @DisplayName("배팅 금액이 1000원 단위가 아닐 때 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1500, 10500})
    void bettingAmount_dividedBy1000(int bettingAmount) {
        assertThatThrownBy(() -> BettingAmount.valueOf(bettingAmount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("베팅 금액은 ")
            .hasMessageContaining("원 단위여야 합니다.");
    }

    @DisplayName("베팅 금액을 생성할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000})
    void createBettingAmount(int bettingAmount) {
        BettingAmount money = BettingAmount.valueOf(bettingAmount);
        assertThat(money.getValue()).isEqualTo(bettingAmount);
    }
}

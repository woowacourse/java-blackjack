package domain.money;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
    @DisplayName("베팅 금액은 10_000_000원 이하여야한다")
    @Test
    void sizeOver(){
        assertThatThrownBy(BettingMoney.of(10000001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 10000000원 이하여야합니다.");
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingResultMoneyTest {
    @Test
    @DisplayName("배팅금액이 1000원 미만이면 예외가 발생한다.")
    void validateBettingMoney() {
        Assertions.assertThatThrownBy(() -> new BettingMoney(999))
                .isInstanceOf(IllegalArgumentException.class);
    }

}

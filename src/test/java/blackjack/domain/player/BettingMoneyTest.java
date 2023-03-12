package blackjack.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {
    @Test
    @DisplayName("100의 단위로 나누어떨어지는 양의 정수가 아닐때 에러 확인")
    void validCheck() {
        Assertions.assertThatThrownBy(() -> new BettingMoney("3050")).isInstanceOf(IllegalArgumentException.class);
    }
}

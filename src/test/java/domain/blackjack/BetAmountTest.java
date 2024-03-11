package domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @DisplayName("배팅 금액으로 1000 미만의 값을 입력했을 때 예외를 발생시킨다.")
    @Test
    void validateMinSize() {
        Assertions.assertThatThrownBy(() -> new BetAmount(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 1000원 이상부터 가능합니다.");
    }
}

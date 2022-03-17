package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetMoneyTest {


    @Test
    @DisplayName("배팅 금액이 음수인 경우, 에러를 발생시킨다.")
    void isNegativeBetMoney() {
        assertThatThrownBy(() -> new BetMoney(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅금액은 음수일 수 없습니다.");
    }

}
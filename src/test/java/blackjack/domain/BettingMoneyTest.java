package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("베팅 금액에 음수가 들어왔을 때 에러를 던지는지 확인한다.")
    void checkNegativeMoney() {
        assertThatThrownBy(() -> new BettingMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 음수를 입력할 수 없습니다.");
    }
    
    @Test
    @DisplayName("베팅 금액이 최소금액의 배수가 아닌 경우 에러를 던지는지 확인한다.")
    void checkNotMultiplesOfLeastUnit() {
        assertThatThrownBy(() -> new BettingMoney(50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 100의 배수로 입력해주세요");
    }
}
package blackJack.domain.User;

import blackJack.utils.ExeptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BettingMoneyTest {

    @Test
    @DisplayName("배팅금액이 음수인 경우 예외를 발생시킨다.")
    void BettingMoney_IsNegative(){
        assertThatThrownBy(() -> new BettingMoney(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
    }

    @Test
    @DisplayName("배팅금액이 0인 경우 예외를 발생시킨다.")
    void BettingMoney_IsZero(){
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
    }

}
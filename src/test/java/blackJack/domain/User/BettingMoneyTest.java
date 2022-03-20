package blackJack.domain.User;

import blackJack.utils.ExeptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @Test
    @DisplayName("플레이어의 배팅금액이 음수인 경우 예외를 발생시킨다.")
    void BettingMoney_PlayerBettingMoneyIsNegative(){
        assertThatThrownBy(() -> new BettingMoney(-1, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
    }

    @Test
    @DisplayName("딜러의 배팅금액이 0인 경우 정상적으로 BettingMoney를 설정한다")
    void BettingMoney_DealerBettingMoneyIsZero(){
        BettingMoney bettingMoney = new BettingMoney(0, true);
        assertThat(bettingMoney.getMoney()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어의 배팅금액이 0인 경우 예외를 발생시킨다.")
    void BettingMoney_PlayerBettingMoneyIsZero(){
        assertThatThrownBy(() -> new BettingMoney(0, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.NOT_ENOUGH_BETTING_MONEY);
    }

}
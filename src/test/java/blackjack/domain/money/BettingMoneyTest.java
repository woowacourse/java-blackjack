package blackjack.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("문자열 기반 베팅금액 생성")
    public void createBettingMoneyFromString() {
        // given & when
        Money money = new BettingMoney("1000");
        
        // then
        Assertions.assertThat(money).isNotNull();
    }
    
    @Test
    @DisplayName("음수로는 배팅금액을 생성할 수 없다.")
    public void throwsExceptionWithNegativeMoney() {
        // given & when
        int amount = -1000;

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new BettingMoney(amount));
    }

}
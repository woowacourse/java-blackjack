package money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BattingMoneyTest {

    @DisplayName("BattingMoney 객체를 생성하는 기능을 테스트한다")
    @Test
    void testInitMoney() {
        //given
        int value = 0;

        //when
        BattingMoney battingMoney = new BattingMoney(value);

        //then
        assertThat(battingMoney).extracting("value").isEqualTo(value);
    }
}

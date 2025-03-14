package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Money;

public class BettingTest {
    @DisplayName("베팅 금액을 정상적으로 지정한다")
    @Test
    void test1() {
        //given
        int price = 10000;

        //when
        Money bettingMoney = new Money(price);

        //then
        Assertions.assertThat(bettingMoney).isEqualTo(new Money(price));
    }
}

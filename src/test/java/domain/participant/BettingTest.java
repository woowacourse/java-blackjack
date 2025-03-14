package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Betting;

public class BettingTest {
    @DisplayName("베팅 금액을 정상적으로 지정한다")
    @Test
    void test1() {
        //given
        int money = 10000;

        //when
        Betting betting = new Betting(10000);

        //then
        Assertions.assertThat(betting).isEqualTo(new Betting(10000));
    }
}

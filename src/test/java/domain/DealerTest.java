package domain;

import domain.participant.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_생성_테스트() {
        //given
        Dealer dealer = new Dealer();

        //when & then
        Assertions.assertThat(dealer).isInstanceOf(Dealer.class);
    }
}

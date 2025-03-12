package object.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_생성_테스트() {
        //given
        Dealer dealer = Dealer.generate();

        //when & then
        Assertions.assertThat(dealer).isInstanceOf(Dealer.class);
    }
}

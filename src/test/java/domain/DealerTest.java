package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_생성_테스트() {
        //given
        String nickname = "히스타";
        Dealer dealer = new Dealer(nickname);

        //when & then
        Assertions.assertThat(dealer).isInstaceOf(Dealer.class);
    }
}

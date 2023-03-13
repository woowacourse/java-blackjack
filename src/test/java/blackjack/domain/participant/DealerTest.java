package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static blackjack.domain.CardConstant.*;

public class DealerTest {

    @Test
    @DisplayName("딜러의 카드의 합이 16이하 인지 테스트")
    void canHitTest() {
        //given
        final Dealer dealer = Dealer.from(new ArrayList<>());

        //when
        dealer.drawCard(CLOVER_SIX);
        dealer.drawCard(DIAMOND_JACK);

        //then
        Assertions.assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16초과 인지 테스트")
    void cantHitTest() {
        //given
        final Dealer dealer = Dealer.from(new ArrayList<>());

        //when
        dealer.drawCard(HEART_SEVEN);
        dealer.drawCard(DIAMOND_JACK);

        //then
        Assertions.assertThat(dealer.canHit()).isFalse();
    }
}

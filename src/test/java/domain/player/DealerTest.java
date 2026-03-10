package domain.player;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러 카드 값의 합이 드로우 임계치 이하인지 true 반환하는지 테스트")
    void 딜러_카드_합_드로우_임계치_이하_테스트() {
        //given
        Dealer dealer = new Dealer("딜러");

        //when
        dealer.addCard(new Card("A", "하트"));

        //then
        Assertions.assertThat(dealer.isBelowDrawThreshold()).isEqualTo(true);
    }
}
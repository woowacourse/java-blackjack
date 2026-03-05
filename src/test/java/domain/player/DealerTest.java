package domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("플레이어에게 카드 정상적으로 나눠주는지 테스트")
    void 딜러_카드_나눔_테스트() {
        //given
        Player player = new Player("pobi");
        Dealer dealer = new Dealer("딜러", 1);

        //when
        dealer.giveCard(player);

        //then
        Assertions.assertThat(player.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러 카드 값의 합이 16이하면 true 반환하는지 테스트")
    void 딜러_카드_합_16_이하_테스트() {
        //given
        Dealer dealer = new Dealer("딜러", 1);

        //when
        dealer.giveCard(dealer);

        //then
        Assertions.assertThat(dealer.isTotalValue16OrLess()).isEqualTo(true);
    }
}
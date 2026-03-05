package domain.player;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
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
}
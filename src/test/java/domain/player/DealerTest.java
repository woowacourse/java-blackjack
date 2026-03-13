package domain.player;

import static util.Constants.DEALER_NAME;

import domain.card.Card;
import domain.player.attribute.Hand;
import domain.player.attribute.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러 카드 값의 합이 16이하면 true 반환하는지 테스트")
    void 딜러_카드_합_16_이하_테스트() {
        //given
        Name name = new Name(DEALER_NAME);
        Hand hand = new Hand();
        Dealer dealer = new Dealer(name, hand);

        //when
        dealer.addCard(new Card("A", "하트"));

        //then
        Assertions.assertThat(dealer.isTotalScore16OrLess()).isEqualTo(true);
    }
}
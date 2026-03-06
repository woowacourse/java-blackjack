package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 보유한지 테스트")
    void 카드_2장_보유_테스트() {
        Cards cards = Cards.of();
        Dealer dealer = Dealer.of(cards.drawInitialHand());

        int cardSize = dealer.getCardsInfo().size();

        int expect = 2;
        assertThat(cardSize).isEqualTo(expect);
    }

}
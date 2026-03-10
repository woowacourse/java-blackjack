package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 정상 분배하는지 확인한다.")
    void 카드_초기_draw시_2장_보유_확인_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        Dealer dealer = Dealer.of(deck.drawInitialHand());

        int cardSize = dealer.getCardSize();

        int expect = 2;
        assertThat(cardSize).isEqualTo(expect);
    }

}
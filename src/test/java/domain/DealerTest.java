package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        Cards cards = Cards.of();
        Dealer dealer = Dealer.of(cards.drawInitialHand());

        assertThat(dealer.getCardsInfo()).hasSize(2);
    }

}
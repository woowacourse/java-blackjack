package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 16이하이면 카드를 추가로 받는다.")
    void canReceive() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Deck.CLOVER_FOUR);
        dealer.receiveCard(Deck.CLOVER_EIGHT);

        assertThat(dealer.canReceive()).isTrue();
    }
}

package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 16이하이면 카드를 추가로 받는다.")
    void canReceive() {
        Cards cards = new Cards(List.of(Deck.CLOVER_FOUR, Deck.CLOVER_EIGHT));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canReceive()).isTrue();
    }
}

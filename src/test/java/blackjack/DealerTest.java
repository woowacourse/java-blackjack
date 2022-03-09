package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.KING);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Dealer dealer = new Dealer(card1, card2); // 2장이 배분되었다

        // 나누어진걸 확인해라
        assertThat(dealer.countCards()).isEqualTo(21);
    }
}

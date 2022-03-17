package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class DealerTest {

    @Test
    @DisplayName("카드의 합이 16 이하여서 뽑을 수 있는지 확인")
    void canDrawTest() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.THREE, Suit.CLUBS));

        assertThat(dealer.canDraw()).isTrue();
    }
}
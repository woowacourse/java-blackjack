package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Nested
class DealerTest {

    @Nested
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest{

        @Test
        @DisplayName("딜러는 한 장의 카드를 오픈할 수 있다.")
        void openFirstCard() {
            Dealer dealer = new Dealer();

            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            dealer.addCards(card1, card2);

            assertThat(dealer.openFirstCard()).isEqualTo(card1);
        }
    }
}
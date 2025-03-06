package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
class DealerTest {

    @Nested
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest {

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

    @Nested
    @DisplayName("딜러 추가 배부 테스트")
    class ExtraCardTest {

        @Test
        @DisplayName("카드의 합이 16 이하이면 추가 배부 받을 수 있다.")
        void extraCard_SumUnder16() {
            Dealer dealer = new Dealer();

            Card card1 = new Card(Suit.HEART, Denomination.FIVE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            dealer.addCards(card1, card2);

            assertThat(dealer.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("카드의 합이 16 초과면 추가 배부 받을 수 없다.")
        void extraCard_SumOver16() {
            Dealer dealer = new Dealer();

            Card card1 = new Card(Suit.HEART, Denomination.SEVEN);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            dealer.addCards(card1, card2);

            assertThat(dealer.isPossibleToAdd()).isFalse();
        }
    }
}
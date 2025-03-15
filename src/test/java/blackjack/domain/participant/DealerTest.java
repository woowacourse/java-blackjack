package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest {

        @Test
        @DisplayName("딜러는 초기 카드를 한 장만 오픈할 수 있다.")
        void openFirstCard() {
            Dealer dealer = new Dealer();

            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);
            dealer.addCards(card1, card2);

            List<Card> cards = dealer.openInitialCards();
            assertAll(() -> {
                assertThat(cards).hasSize(1);
                assertThat(cards.getFirst()).isEqualTo(card1);
            });
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

package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Nested
    @DisplayName("카드 상태 테스트")
    class CardStateTest {

        @Test
        @DisplayName("카드가 블랙잭이라면 true를 반환한다")
        void ifBlackjackTrue() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.ACE));

            assertThat(handCards.isBlackjack()).isTrue();
        }

        @Test
        @DisplayName("카드가 블랙잭이 아니라면 false를 반환한다")
        void ifBlackjackFalse() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.EIGHT));

            assertThat(handCards.isBlackjack()).isFalse();
        }

        @Test
        @DisplayName("카드가 버스트라면 true를 반환한다")
        void ifBustTrue() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.JACK));
            handCards.addCard(new Card(Suit.HEART, Denomination.THREE));

            assertThat(handCards.isBust()).isTrue();
        }

        @Test
        @DisplayName("카드가 버스트가 아니라면 false를 반환한다")
        void ifBustFalse() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.EIGHT));
            handCards.addCard(new Card(Suit.DIAMOND, Denomination.TWO));

            assertThat(handCards.isBust()).isFalse();
        }
    }

    @Nested
    @DisplayName("카드 합 테스트")
    class CardSumTest {

        @Test
        @DisplayName("카드 합을 구할 수 있다")
        void sumCards() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.JACK));

            assertThat(handCards.calculateDenominations()).isEqualTo(20);
        }

        @Test
        @DisplayName("에이스를 1로 처리해 카드 합을 구할 수 있다")
        void sumCardsWithAceValueOne() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.DIAMOND, Denomination.QUEEN));
            handCards.addCard(new Card(Suit.CLUB, Denomination.ACE));

            assertThat(handCards.calculateDenominations()).isEqualTo(21);
        }

        @Test
        @DisplayName("에이스를 11로 처리해 카드 합을 구할 수 있다")
        void sumCardsWithAceValueEleven() {
            HandCards handCards = new HandCards();
            handCards.addCard(new Card(Suit.SPADE, Denomination.TEN));
            handCards.addCard(new Card(Suit.DIAMOND, Denomination.EIGHT));
            handCards.addCard(new Card(Suit.CLUB, Denomination.ACE));

            assertThat(handCards.calculateDenominations()).isEqualTo(19);
        }
    }
}

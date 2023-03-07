package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드는 자신의 점수를 반환한다.")
    void getScore() {
        Card card = new Card(Denomination.KING, Suit.SPADE);
        Denomination denomination = card.getDenomination();
        Suit suit = card.getSuit();
        Assertions.assertThat(denomination).isEqualTo(Denomination.KING);
        Assertions.assertThat(suit).isEqualTo(Suit.SPADE);
    }

    @DisplayName("equals로 비교한다.")
    @Nested
    class Equals {

        @DisplayName("같을 때")
        @Test
        void same() {
            Card card1 = new Card(Denomination.KING, Suit.SPADE);
            Card card2 = new Card(Denomination.KING, Suit.SPADE);
            Assertions.assertThat(card1.equals(card2)).isTrue();
        }

        @DisplayName("문양이 다를 때")
        @Test
        void differentSuit() {
            Card card1 = new Card(Denomination.KING, Suit.SPADE);
            Card card2 = new Card(Denomination.KING, Suit.HEART);
            Assertions.assertThat(card1.equals(card2)).isFalse();
        }

        @DisplayName("숫자가 다를 때")
        @Test
        void differentDenomination() {
            Card card1 = new Card(Denomination.KING, Suit.SPADE);
            Card card2 = new Card(Denomination.QUEEN, Suit.SPADE);
            Assertions.assertThat(card1.equals(card2)).isFalse();
        }
    }
}

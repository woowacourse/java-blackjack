package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("원하는 끗수와 문양의 카드를 생성한다.")
    @Test
    void createCard() {
        Card card = new Card(Denomination.ACE, Suit.DIAMOND);
        Assertions.assertThat(card.getDenomination()).isEqualTo(Denomination.ACE);
        Assertions.assertThat(card).extracting("suit").isEqualTo(Suit.DIAMOND);
    }

    @DisplayName("카드의 끗수의 표기명을 반환한다.")
    @Nested
    class DenominationName {

        @DisplayName("ACE는 A이다.")
        @Test
        void ace() {
            Card card = new Card(Denomination.ACE, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("A");
        }

        @DisplayName("TWO는 2이다.")
        @Test
        void two() {
            Card card = new Card(Denomination.TWO, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("2");
        }

        @DisplayName("THREE는 3이다.")
        @Test
        void three() {
            Card card = new Card(Denomination.THREE, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("3");
        }

        @DisplayName("FOUR는 4이다.")
        @Test
        void four() {
            Card card = new Card(Denomination.FOUR, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("4");
        }

        @DisplayName("FIVE는 5이다.")
        @Test
        void five() {
            Card card = new Card(Denomination.FIVE, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("5");
        }

        @DisplayName("SIX는 6이다.")
        @Test
        void six() {
            Card card = new Card(Denomination.SIX, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("6");
        }

        @DisplayName("SEVEN은 7이다.")
        @Test
        void seven() {
            Card card = new Card(Denomination.SEVEN, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("7");
        }

        @DisplayName("EIGHT은 8이다.")
        @Test
        void eight() {
            Card card = new Card(Denomination.EIGHT, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("8");
        }

        @DisplayName("NINE은 9이다.")
        @Test
        void nine() {
            Card card = new Card(Denomination.NINE, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("9");
        }

        @DisplayName("TEN는 10이다.")
        @Test
        void ten() {
            Card card = new Card(Denomination.TEN, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("10");
        }

        @DisplayName("JACK은 J이다.")
        @Test
        void jack() {
            Card card = new Card(Denomination.JACK, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("J");
        }

        @DisplayName("QUEEN은 Q이다.")
        @Test
        void queen() {
            Card card = new Card(Denomination.QUEEN, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("Q");
        }

        @DisplayName("KING은 K이다.")
        @Test
        void king() {
            Card card = new Card(Denomination.KING, Suit.DIAMOND);
            Assertions.assertThat(card.getDenomination().getName()).isEqualTo("K");
        }
    }

    @DisplayName("카드의 포인트를 반환한다")
    @Nested
    class DenominationPoint {

        @DisplayName("ACE는 1을 반환한다.")
        @Test
        void ace() {
            Card card = new Card(Denomination.ACE, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(1);
        }

        @DisplayName("TWO는 2을 반환한다.")
        @Test
        void two() {
            Card card = new Card(Denomination.TWO, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(2);
        }

        @DisplayName("THREE는 3을 반환한다.")
        @Test
        void three() {
            Card card = new Card(Denomination.THREE, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(3);
        }

        @DisplayName("FOUR는 4을 반환한다.")
        @Test
        void four() {
            Card card = new Card(Denomination.FOUR, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(4);
        }

        @DisplayName("FIVE는 5을 반환한다.")
        @Test
        void five() {
            Card card = new Card(Denomination.FIVE, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(5);
        }

        @DisplayName("SIX는 6을 반환한다.")
        @Test
        void six() {
            Card card = new Card(Denomination.SIX, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(6);
        }

        @DisplayName("SEVEN은 7을 반환한다.")
        @Test
        void seven() {
            Card card = new Card(Denomination.SEVEN, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(7);
        }

        @DisplayName("EIGHT은 8을 반환한다.")
        @Test
        void eight() {
            Card card = new Card(Denomination.EIGHT, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(8);
        }

        @DisplayName("NINE은 9을 반환한다.")
        @Test
        void nine() {
            Card card = new Card(Denomination.NINE, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(9);
        }

        @DisplayName("TEN는 10을 반환한다.")
        @Test
        void ten() {
            Card card = new Card(Denomination.TEN, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(10);
        }

        @DisplayName("JACK은 10을 반환한다.")
        @Test
        void jack() {
            Card card = new Card(Denomination.JACK, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(10);
        }

        @DisplayName("QUEEN은 10을 반환한다.")
        @Test
        void queen() {
            Card card = new Card(Denomination.QUEEN, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(10);
        }

        @DisplayName("KING은 10을 반환한다.")
        @Test
        void king() {
            Card card = new Card(Denomination.KING, Suit.DIAMOND);
            Assertions.assertThat(card.getPoint()).isEqualTo(10);
        }

    }
}

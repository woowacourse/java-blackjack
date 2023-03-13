package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import blackjack.domain.result.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandTest {
    @Test
    @DisplayName("8 + 5 = 13")
    void score() {
        Hand hand = new Hand(Card.of(Suit.DIAMOND, Letter.EIGHT), Card.of(Suit.CLOVER, Letter.FIVE));
        assertThat(hand.calculateScore()).isEqualTo(new Score(13));
    }

    @Test
    @DisplayName("blackjack 확인")
    void isBlackJack() {
        Hand hand = new Hand(Card.of(Suit.DIAMOND, Letter.ACE), Card.of(Suit.HEART, Letter.TEN));
        assertThat(hand.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("bust 확인")
    void isBust() {
        Hand hand = new Hand(Card.of(Suit.HEART, Letter.EIGHT),
                Card.of(Suit.CLOVER, Letter.JACK), Card.of(Suit.DIAMOND, Letter.EIGHT));
        assertThat(hand.isBust()).isTrue();
    }

    @Nested
    @DisplayName("ACE 점수 계산")
    class AceScore {
        @Test
        @DisplayName("ACE + 2 = 13")
        void ace1() {
            Hand hand = new Hand(Card.of(Suit.CLOVER, Letter.ACE), Card.of(Suit.DIAMOND, Letter.TWO));
            assertThat(hand.calculateScore()).isEqualTo(new Score(13));
        }

        @Test
        @DisplayName("ACE + ACE = 12")
        void ace2() {
            Hand hand = new Hand(Card.of(Suit.DIAMOND, Letter.ACE), Card.of(Suit.CLOVER, Letter.ACE));
            assertThat(hand.calculateScore()).isEqualTo(new Score(12));
        }

        @Test
        @DisplayName("ACE + ACE + ACE + ACE = 14")
        void ace3() {
            Hand hand = new Hand(Card.of(Suit.DIAMOND, Letter.ACE), Card.of(Suit.CLOVER, Letter.ACE),
                    Card.of(Suit.HEART, Letter.ACE), Card.of(Suit.SPADE, Letter.ACE));
            assertThat(hand.calculateScore()).isEqualTo(new Score(14));
        }

        @Test
        @DisplayName("ACE + ACE + ACE + ACE + ACE = 15")
        void ace4() {
            Hand hand = new Hand(Card.of(Suit.SPADE, Letter.ACE), Card.of(Suit.HEART, Letter.ACE),
                    Card.of(Suit.HEART, Letter.ACE), Card.of(Suit.DIAMOND, Letter.ACE), Card.of(Suit.HEART, Letter.ACE));
            assertThat(hand.calculateScore()).isEqualTo(new Score(15));
        }

        @Test
        @DisplayName("ACE + 10 = 21")
        void ace5() {
            Hand hand = new Hand(Card.of(Suit.HEART, Letter.ACE), Card.of(Suit.DIAMOND, Letter.TEN));
            assertThat(hand.calculateScore()).isEqualTo(new Score(21));
        }
    }
}

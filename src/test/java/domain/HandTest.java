package domain;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    @DisplayName("플레이어에게 카드를 추가할 수 있다")
    void whenGivingCard_thenSuccess() {
        Card card = new Card(Suit.CLOVER, Denomination.THREE);

        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("플레이어 점수를 가져올 수 있다.")
    void givenCard_whenGetScore() {
        Hand hand = new Hand(List.of(new Card(Suit.SPADE, Denomination.THREE)));
        assertThat(hand.getTotalScore()).isEqualTo(3);
    }

    @Nested
    @DisplayName("1과 11 점수를 유동적으로 갖는 ACE 카드 관련 테스트")
    class AceCase {
        @Test
        @DisplayName("나머지 점수가 10 이하인 경우, Ace의 점수를 11로 한다.")
        void givenScore_whenOrLessTen() {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Suit.SPADE, Denomination.THREE));
            cards.add(new Card(Suit.HEART, Denomination.ACE));
            cards.add(new Card(Suit.SPADE, Denomination.SEVEN));
            Hand hand = new Hand(cards);

            assertThat(hand.getTotalScore()).isEqualTo(21);
        }

        @Test
        @DisplayName("나머지 점수가 11 이상인 경우, Ace의 점수를 1로 한다.")
        void givenScore_whenOrMoreEleven() {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Suit.SPADE, Denomination.THREE));
            cards.add(new Card(Suit.HEART, Denomination.ACE));
            cards.add(new Card(Suit.SPADE, Denomination.EIGHT));
            Hand hand = new Hand(cards);

            assertThat(hand.getTotalScore()).isEqualTo(12);
        }

        @Test
        @DisplayName("ACE만 여러 장 있을 경우, 최초의 카드는 11점이고 그 이후로는 1점으로 계산된다.")
        void givenMultipleAce_whenCountingScore() {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Suit.DIAMOND, Denomination.ACE));
            cards.add(new Card(Suit.HEART, Denomination.ACE));
            cards.add(new Card(Suit.SPADE, Denomination.ACE));
            cards.add(new Card(Suit.CLOVER, Denomination.ACE));
            Hand hand = new Hand(cards);

            assertThat(hand.getTotalScore()).isEqualTo(14);
        }

        @Test
        @DisplayName("ACE 여러 장과 다른 카드가 있을 경우, 나머지 카드의 점수에 따라 유동적으로 조정한다.")
        void givenMultipleAceAndOther_whenCountingScore() {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Suit.DIAMOND, Denomination.ACE));
            cards.add(new Card(Suit.HEART, Denomination.EIGHT));
            cards.add(new Card(Suit.CLOVER, Denomination.ACE));
            Hand hand = new Hand(cards);

            assertThat(hand.getTotalScore()).isEqualTo(20);
        }
    }

    @Test
    @DisplayName("점수가 21을 초과했을 경우 Burst 된다.")
    void givenOverTwentyOneScore_thenBurst() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Denomination.KING));
        cards.add(new Card(Suit.SPADE, Denomination.QUEEN));
        cards.add(new Card(Suit.SPADE, Denomination.TWO));
        Hand hand = new Hand(cards);

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하일 경우 Burst되지 않는다.")
    void givenOrLessTwentyOneScore_thenNotBurst() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Denomination.KING));
        cards.add(new Card(Suit.SPADE, Denomination.ACE));
        cards.add(new Card(Suit.SPADE, Denomination.QUEEN));
        Hand hand = new Hand(cards);

        assertThat(hand.isBust()).isFalse();
    }
}
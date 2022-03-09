package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    public static final Card aceCard = new Card(Denomination.ACE, Suit.CLOVER);

    @Test
    @DisplayName("카드를 가져와서 나의 패(Hand)를 만든다")
    void make_hand() {
        Card card = new Card(Denomination.TWO, Suit.SPADE);
        Card card2 = new Card(Denomination.JACK, Suit.DIAMOND);

        Hand hand = new Hand();
        hand.add(card, card2);

        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드팩의 합계를 구한다")
    void sum_hand() {
        Card card = new Card(Denomination.TWO, Suit.SPADE);
        Card card2 = new Card(Denomination.JACK, Suit.DIAMOND);

        Hand hand = new Hand();
        hand.add(card, card2);

        assertThat(hand.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드팩의 합계를 구한다")
    void sum_hand_other_case() {
        Card card = new Card(Denomination.FOUR, Suit.SPADE);
        Card card2 = new Card(Denomination.JACK, Suit.DIAMOND);

        Hand hand = new Hand();
        hand.add(card, card2);

        assertThat(hand.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("21점이 넘을 경우 버스트 이다.")
    void isBust() {
        Card card1 = new Card(Denomination.KING, Suit.SPADE);
        Card card2 = new Card(Denomination.JACK, Suit.SPADE);
        Card card3 = new Card(Denomination.TWO, Suit.SPADE);

        Hand hand = new Hand();
        hand.add(card1, card2, card3);

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("21점이 넘지 않을 경우 버스트가 아니다.")
    void isNotBust() {
        Card card1 = new Card(Denomination.KING, Suit.SPADE);
        Card card2 = new Card(Denomination.EIGHT, Suit.SPADE);
        Card card3 = new Card(Denomination.TWO, Suit.SPADE);

        Hand hand = new Hand();
        hand.add(card1, card2, card3);

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 2장이고 21점이면 블랙잭이다.")
    void isBlackjack() {
        Card card1 = new Card(Denomination.ACE, Suit.SPADE);
        Card card2 = new Card(Denomination.TEN, Suit.CLOVER);

        Hand hand = new Hand();
        hand.add(card1, card2);

        assertThat(hand.isBlackjack()).isTrue();
    }

    @DisplayName("Ace가 포함된 경우 점수 계산이 정확한지 확인")
    @ParameterizedTest
    @MethodSource("hasAceCardHandScoreTestCase")
    void test(Hand hand, int expected) {
        assertThat(hand.getScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> hasAceCardHandScoreTestCase() {
        return Stream.of(
                Arguments.of(createHand(aceCard, new Card(Denomination.TEN, Suit.SPADE)), 21),
                Arguments.of(createHand(aceCard, new Card(Denomination.KING, Suit.SPADE)), 21),
                Arguments.of(createHand(aceCard, new Card(Denomination.JACK, Suit.DIAMOND)), 21),
                Arguments.of(createHand(aceCard, new Card(Denomination.QUEEN, Suit.CLOVER)), 21),
                Arguments.of(createHand(aceCard, aceCard, new Card(Denomination.NINE, Suit.CLOVER)), 21),
                Arguments.of(createHand(aceCard, aceCard, aceCard, new Card(Denomination.EIGHT, Suit.CLOVER)), 21),
                Arguments.of(createHand(aceCard, new Card(Denomination.SEVEN, Suit.HEART)), 18)
        );
    }

    private static Hand createHand(Card... cards) {
        Hand hand = new Hand();
        hand.add(cards);
        return hand;
    }
}

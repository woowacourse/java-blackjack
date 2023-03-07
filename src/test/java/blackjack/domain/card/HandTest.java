package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    @DisplayName("카드를 추가하면 크기가 1 증가한다.")
    void addCard() {
        Hand hand = new Hand();
        int expectedSize = hand.getCount() + 1;

        hand.add(Card.of(Suit.SPADE, Denomination.ACE));

        assertThat(hand.getCount()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @MethodSource("generateHand")
    @DisplayName("보유한 카드의 점수 합을 계산한다.")
    void getSum(List<Card> createdHand, int expectedSum) {
        Hand hand = new Hand();

        for (Card card : createdHand) {
            hand.add(card);
        }

        assertThat(hand.sum().getValue()).isEqualTo(expectedSum);
    }

    static Stream<Arguments> generateHand() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.CLOVER, Denomination.TWO)), 9),
                Arguments.of(List.of(Card.of(Suit.HEART, Denomination.NINE), Card.of(Suit.HEART, Denomination.TWO)), 11),
                Arguments.of(List.of(Card.of(Suit.DIAMOND, Denomination.TEN), Card.of(Suit.SPADE, Denomination.TEN)), 20),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.THREE), Card.of(Suit.CLOVER, Denomination.TWO)), 5),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.SEVEN), Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.DIAMOND, Denomination.SEVEN)), 21),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.TEN), Card.of(Suit.SPADE, Denomination.ACE), Card.of(Suit.DIAMOND, Denomination.SEVEN)), 28)
        );
    }

    @ParameterizedTest
    @MethodSource("generateHandWithACE")
    @DisplayName("에이스의 카드 개수를 구한다.")
    void countACE(List<Card> createdHand, int expectedCount) {
        Hand hand = new Hand();

        for (Card card : createdHand) {
            hand.add(card);
        }

        assertThat(hand.getAceCount()).isEqualTo(expectedCount);
    }

    static Stream<Arguments> generateHandWithACE() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.ACE), Card.of(Suit.CLOVER, Denomination.ACE)), 2),
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.ACE), Card.of(Suit.HEART, Denomination.ACE), Card.of(Suit.CLOVER, Denomination.ACE)), 3),
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.ACE), Card.of(Suit.HEART, Denomination.ACE), Card.of(Suit.CLOVER, Denomination.ACE), Card.of(Suit.DIAMOND, Denomination.ACE)), 4)
        );
    }
}

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

        hand.add(new Card(Suit.SPADE, Denomination.ACE));

        assertThat(hand.getCount()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("보유한 카드의 점수 합을 계산한다.")
    void getSum(List<Card> createdCards, int expectedSum) {
        Hand hand = new Hand();

        for (Card card : createdCards) {
            hand.add(card);
        }

        assertThat(hand.sum()).isEqualTo(expectedSum);
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Suit.SPADE, Denomination.SEVEN), new Card(Suit.CLOVER, Denomination.TWO)), 9),
                Arguments.of(List.of(new Card(Suit.HEART, Denomination.NINE), new Card(Suit.HEART, Denomination.TWO)), 11),
                Arguments.of(List.of(new Card(Suit.DIAMOND, Denomination.TEN), new Card(Suit.SPADE, Denomination.TEN)), 20),
                Arguments.of(List.of(new Card(Suit.CLOVER, Denomination.THREE), new Card(Suit.CLOVER, Denomination.TWO)), 5),
                Arguments.of(List.of(new Card(Suit.CLOVER, Denomination.SEVEN), new Card(Suit.SPADE, Denomination.SEVEN), new Card(Suit.DIAMOND, Denomination.SEVEN)), 21),
                Arguments.of(List.of(new Card(Suit.CLOVER, Denomination.TEN), new Card(Suit.SPADE, Denomination.ACE), new Card(Suit.DIAMOND, Denomination.SEVEN)), 28)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithACE")
    @DisplayName("에이스가 포함된 카드의 점수 합을 계산한다.")
    void getSumWithACE(List<Card> createdCards, int expectedCount) {
        Hand hand = new Hand();

        for (Card card : createdCards) {
            hand.add(card);
        }

        assertThat(hand.getAceCount()).isEqualTo(expectedCount);
    }

    static Stream<Arguments> generateCardsWithACE() {
        return Stream.of(
                Arguments.of(List.of(new Card(Suit.SPADE, Denomination.ACE), new Card(Suit.CLOVER, Denomination.ACE)), 2),
                Arguments.of(List.of(new Card(Suit.SPADE, Denomination.ACE), new Card(Suit.HEART, Denomination.ACE), new Card(Suit.CLOVER, Denomination.ACE)), 3),
                Arguments.of(List.of(new Card(Suit.SPADE, Denomination.ACE), new Card(Suit.HEART, Denomination.ACE), new Card(Suit.CLOVER, Denomination.ACE), new Card(Suit.DIAMOND, Denomination.ACE)), 4)
        );
    }
}

package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardHandTest {
    @DisplayName("카드에 ACE가 있다면 가능한 max값을 계산하여 판단한다")
    @ParameterizedTest
    @MethodSource("hasAce")
    void test(List<Card> cards, int expectedSum) {
        //given
        CardHand cardHand = new CardHand();
        for (Card card : cards) {
            cardHand.add(card);
        }

        // when
        int realSum = cardHand.calculateScore();

        // then
        Assertions.assertThat(realSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.ACE),
                        new Card(CardShape.HEART, CardRank.ACE)), 13),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.HEART, CardRank.ACE)), 21),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.Q)), 21)
        );
    }

    @DisplayName("카드의 총합을 계산한다.")
    @ParameterizedTest
    @MethodSource("cardDeck")
    void test2(List<Card> cards, int expectedSum) {
        // given
        CardHand cardDeck = new CardHand();
        for (Card card : cards) {
            cardDeck.add(card);
        }

        // when
        int realSum = cardDeck.calculateScore();

        // then
        Assertions.assertThat(realSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> cardDeck() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.ACE),
                        new Card(CardShape.HEART, CardRank.ACE)), 13),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.HEART, CardRank.ACE)), 21),
                Arguments.arguments(List.of(
                        new Card(CardShape.HEART, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.Q)), 21),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.J),
                        new Card(CardShape.CLOVER, CardRank.Q)), 20),
                Arguments.arguments(List.of(
                        new Card(CardShape.SPADE, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.CLOVER, CardRank.Q)), 30)
        );
    }

    @DisplayName("블랙잭인지 아닌지 판단한다")
    @Test
    void test3() {
        // given
        Card trumpCard1 = new Card(CardShape.CLOVER, CardRank.ACE);
        Card trumpCard2 = new Card(CardShape.CLOVER, CardRank.J);
        CardHand cardDeck = new CardHand();

        cardDeck.add(trumpCard1);
        cardDeck.add(trumpCard2);

        // when
        boolean isBlackjack = cardDeck.isBlackjack();

        // then
        Assertions.assertThat(isBlackjack).isEqualTo(true);
    }
}
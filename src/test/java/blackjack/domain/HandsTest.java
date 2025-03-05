package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {
    @DisplayName("카드의 합을 계산한다")
    @MethodSource("returnCardsAndSum")
    @ParameterizedTest
    void aa(List<Card> cards, int sum) {
        // given
        Hands hands = new Hands();
        for (Card card : cards) {
            hands.addNewCard(card);
        }

        // when
        int result = hands.calculateSum();

        // then
        assertThat(result).isEqualTo(sum);
    }

    static Stream<Arguments> returnCardsAndSum() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.TEN), new Card(CardShape.HEART, CardType.EIGHT)),
                        18),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.EIGHT)),
                        19),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.TEN)), 21),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.ACE)), 12),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.TWO), new Card(CardShape.HEART, CardType.THREE),
                                new Card(CardShape.HEART, CardType.ACE)), 16),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.FIVE), new Card(CardShape.HEART, CardType.SIX),
                                new Card(CardShape.HEART, CardType.ACE)), 12),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.FIVE), new Card(CardShape.HEART, CardType.ACE),
                                new Card(CardShape.HEART, CardType.ACE)), 17),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.FIVE), new Card(CardShape.HEART, CardType.ACE),
                                new Card(CardShape.HEART, CardType.ACE)), 17),
                Arguments.arguments(
                        List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.ACE),
                                new Card(CardShape.HEART, CardType.ACE)), 13));
    }

    @DisplayName("카드의 합이 특정 값 이하이면 True를 반환한다")
    @Test
    void isSumBelowTest1() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);

        // when
        boolean result = hands.isSumBelow(21);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드의 합이 특정 값 초과이면 False를 반환한다")
    @Test
    void isSumBelowTest2() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        Card card3 = new Card(CardShape.HEART, CardType.KING);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);
        hands.addNewCard(card3);

        // when
        boolean result = hands.isSumBelow(21);

        // then
        assertThat(result).isFalse();
    }
}

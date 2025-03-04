package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Hands;
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
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.TEN), new Card(CardShape.HEART, CardValue.EIGHT)), 18),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.ACE), new Card(CardShape.HEART, CardValue.EIGHT)), 19),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.ACE), new Card(CardShape.HEART, CardValue.TEN)), 21),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.ACE), new Card(CardShape.HEART, CardValue.ACE)), 12),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.TWO), new Card(CardShape.HEART, CardValue.THREE), new Card(CardShape.HEART, CardValue.ACE)), 16),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.FIVE), new Card(CardShape.HEART, CardValue.SIX), new Card(CardShape.HEART, CardValue.ACE)), 12),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.FIVE), new Card(CardShape.HEART, CardValue.ACE), new Card(CardShape.HEART, CardValue.ACE)), 17),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.FIVE), new Card(CardShape.HEART, CardValue.ACE), new Card(CardShape.HEART, CardValue.ACE)), 17),
                Arguments.arguments(List.of(new Card(CardShape.CLOVER, CardValue.ACE), new Card(CardShape.HEART, CardValue.ACE), new Card(CardShape.HEART, CardValue.ACE)), 13));
    }

    @DisplayName("카드의 합이 특정 값 이하이면 True를 반환한다")
    @Test
    void isSumBelowTest1() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);

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
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);
        Card card3 = new Card(CardShape.HEART, CardValue.KING);

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

package blackjack.domain.gambler;

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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {
    @DisplayName("카드의 합이 특정 값 이하인 지 여부를 반환한다")
    @CsvSource(value = {"21:True", "18:True", "17:False"}, delimiterString = ":")
    @ParameterizedTest
    void isScoreBelowTest(int score, boolean expected) {
        // given
        Hands hands = new Hands();
        hands.addCard(new Card(CardShape.CLOVER, CardType.TEN));
        hands.addCard(new Card(CardShape.HEART, CardType.EIGHT));

        // when
        boolean result = hands.isScoreBelow(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("카드의 합을 계산한다")
    @Test
    void calculateScoreTest() {
        // given
        Hands hands = new Hands();
        hands.addCard(new Card(CardShape.CLOVER, CardType.TEN));
        hands.addCard(new Card(CardShape.HEART, CardType.EIGHT));

        // when
        int result = hands.calculateScore();

        // then
        assertThat(result).isEqualTo(18);
    }

    @DisplayName("에이스를 고려하여 카드의 합을 계산한다")
    @MethodSource("returnCardsContainsAceAndSum")
    @ParameterizedTest
    void calculateScoreConsiderAceTest(List<Card> cards, int sum) {
        // given
        Hands hands = new Hands();
        for (Card card : cards) {
            hands.addCard(card);
        }

        // when
        int result = hands.calculateScore();

        // then
        assertThat(result).isEqualTo(sum);
    }

    static Stream<Arguments> returnCardsContainsAceAndSum() {
        Arguments arguments1 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.EIGHT)), 19);
        Arguments arguments2 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.TEN)), 21);
        Arguments arguments3 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.ACE), new Card(CardShape.HEART, CardType.ACE)), 12);
        Arguments arguments4 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.TWO),
                        new Card(CardShape.HEART, CardType.THREE),
                        new Card(CardShape.HEART, CardType.ACE)), 16);
        Arguments arguments5 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.FIVE),
                        new Card(CardShape.HEART, CardType.SIX),
                        new Card(CardShape.HEART, CardType.ACE)), 12);
        Arguments arguments6 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.FIVE),
                        new Card(CardShape.HEART, CardType.ACE),
                        new Card(CardShape.HEART, CardType.ACE)), 17);
        Arguments arguments7 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.FIVE),
                        new Card(CardShape.HEART, CardType.ACE),
                        new Card(CardShape.HEART, CardType.ACE)), 17);
        Arguments arguments8 = Arguments.arguments(
                List.of(new Card(CardShape.CLOVER, CardType.ACE),
                        new Card(CardShape.HEART, CardType.ACE),
                        new Card(CardShape.HEART, CardType.ACE)), 13);
        return Stream.of(arguments1, arguments2, arguments3, arguments4, arguments5, arguments6, arguments7,
                arguments8);
    }
}

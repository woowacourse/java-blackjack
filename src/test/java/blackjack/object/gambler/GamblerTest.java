package blackjack.object.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.object.card.Card;
import blackjack.object.card.CardShape;
import blackjack.object.card.CardType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class GamblerTest {
    @DisplayName("카드의 합이 특정 값 이하인 지 여부를 반환한다")
    @CsvSource(value = {"21:True", "18:True", "17:False"}, delimiterString = ":")
    @ParameterizedTest
    void isScoreBelowTest(int score, boolean expected) {
        // given
        Player player = new Player(new Name("라젤"));
        player.addCard(new Card(CardShape.CLOVER, CardType.TEN));
        player.addCard(new Card(CardShape.HEART, CardType.EIGHT));

        // when
        boolean result = player.isScoreBelow(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("카드의 합을 계산한다")
    @Test
    void calculateScoreTest() {
        // given
        Player player = new Player(new Name("라젤"));
        player.addCard(new Card(CardShape.CLOVER, CardType.TEN));
        player.addCard(new Card(CardShape.HEART, CardType.EIGHT));

        // when
        int result = player.calculateScore();

        // then
        assertThat(result).isEqualTo(18);
    }

    @DisplayName("에이스를 고려하여 카드의 합을 계산한다")
    @MethodSource("returnCardsContainsAceAndSum")
    @ParameterizedTest
    void calculateScoreConsiderAceTest(List<Card> cards, int sum) {
        // given
        Player player = new Player(new Name("라젤"));
        for (Card card : cards) {
            player.addCard(card);
        }

        // when
        int result = player.calculateScore();

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

    @DisplayName("에이스를 고려해서 총합을 조정한다")
    @CsvSource(value = {"18,0,18", "23,1,13", "21,1,21", "18,1,18", "24,2,14", "22,2,12"})
    @ParameterizedTest
    void adjustSumByAceTest(int sum, int aceCount, int expected) {
        Player player = new Player(new Name("라젤"));
        assertThat(player.adjustSumByAce(sum, aceCount)).isEqualTo(expected);
    }
}

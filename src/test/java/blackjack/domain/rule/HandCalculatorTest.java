package blackjack.domain.rule;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandCalculatorTest {

    @ParameterizedTest
    @MethodSource("createCards")
    @DisplayName("카드의 합을 구하는 테스트")
    void calculateSum(List<Card> cards, int result) {
        assertThat(HandCalculator.calculate(cards)).isEqualTo(result);
    }

    private static Stream<Arguments> createCards() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card fiveClover = new Card(CardSymbol.FIVE, CardType.CLOVER);
        Card queenClover = new Card(CardSymbol.QUEEN, CardType.CLOVER);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> cardSumSixteen = Arrays.asList(aceSpade, fiveClover);
        List<Card> cardSumTwentyOne = Arrays.asList(aceSpade, queenClover, kingClover);
        List<Card> cardSumThirteen = Arrays.asList(aceSpade, aceSpade, aceSpade);
        return Stream.of(
                Arguments.of(cardSumSixteen, 16),
                Arguments.of(cardSumTwentyOne, 21),
                Arguments.of(cardSumThirteen, 13));
    }
}
package blackjack.score.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.card.domain.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    private static Stream<Arguments> findByCardProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.ACE), ScoreCalculator.ACE, 11),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN), ScoreCalculator.ACE, 21),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.NINE), ScoreCalculator.ACE, 20),
                Arguments.of(aCardBundle(CardNumber.TEN, CardNumber.TEN), ScoreCalculator.DEFAULT, 20),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN, CardNumber.ACE), ScoreCalculator.ACE, 12)
        );
    }

    private static Stream<Arguments> calculateScoreProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.ACE), 11),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN), 21),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.NINE), 20),
                Arguments.of(aCardBundle(CardNumber.TEN, CardNumber.TEN), 20),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN, CardNumber.ACE), 12)
        );
    }

    @DisplayName("Card 리스트에 해당하는 계산 전략 찾기")
    @ParameterizedTest
    @MethodSource("findByCardProvider")
    void findByCardBundle(CardBundle cardBundle, ScoreCalculator result) {
        //given
        List<Card> cards = cardBundle.getCards();

        //when
        ScoreCalculator expect = ScoreCalculator.findByCards(cards);

        //then
        assertThat(expect).isEqualTo(result);
    }

    @DisplayName("Card 리스트에 해당하는 계산 결과")
    @ParameterizedTest
    @MethodSource("calculateScoreProvider")
    void calculate(CardBundle cardBundle, int result) {
        //given
        List<Card> cards = cardBundle.getCards();

        //when
        ScoreCalculator scoreCalculator = ScoreCalculator.findByCards(cards);
        int expect = scoreCalculator.calculate(cards);

        //then
        assertThat(expect).isEqualTo(result);
    }
}
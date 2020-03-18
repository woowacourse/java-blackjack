package blackjack.domain.calculator;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;

class AceScoreStrategyTest {

    private static Stream<Arguments> supportCardBundleProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.ACE), ScoreCalculator.ACE),
                Arguments.of(aCardBundle(CardNumber.TWO), ScoreCalculator.DEFAULT)
        );
    }

    private static Stream<Arguments> calculateCardBundleProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.ACE), 11),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.ACE), 12),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.ACE, CardNumber.TEN), 12),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE), 13)
        );
    }

    @DisplayName("Ace가 있으면 지원한다.")
    @ParameterizedTest
    @MethodSource("supportCardBundleProvider")
    void support(CardBundle cardBundle, ScoreCalculator result) {

        assertThat(ScoreCalculator.findByCardBundle(cardBundle)).isEqualTo(result);
    }

    @DisplayName("Ace가 존재하는 경우 합이 21을 넘지 않는 한 Ace는 1이 아닌 11로 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateCardBundleProvider")
    void calculate(CardBundle cardBundle, int result) {
        //given
        ScoreCalculator calculator = ScoreCalculator.findByCardBundle(cardBundle);

        //when
        int expect = calculator.calculate(cardBundle);

        //given
        assertThat(expect).isEqualTo(result);
    }
}
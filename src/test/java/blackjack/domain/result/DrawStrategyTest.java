package blackjack.domain.result;

import blackjack.domain.card.CardBundle;
import blackjack.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.CardBundleHelper.aCardBundle;
import static blackjack.domain.card.component.CardNumber.ACE;
import static blackjack.domain.card.component.CardNumber.KING;
import static org.assertj.core.api.Assertions.assertThat;

class DrawStrategyTest {

    private static Stream<Arguments> drawEnoughProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(ACE, KING), aCardBundle(ACE, KING), true),
                Arguments.of(aCardBundle(ACE, KING), aCardBundle(ACE), false),
                Arguments.of(aCardBundle(ACE), aCardBundle(ACE, KING), false),
                Arguments.of(aCardBundle(ACE), aCardBundle(ACE), false)
        );
    }

    @DisplayName("무조건 무승부 조건, 딜러와 갬블러 모두 블랙잭인 경우")
    @ParameterizedTest
    @MethodSource("drawEnoughProvider")
    void enough(CardBundle dealerCardBundle, CardBundle gamblerCardBundle, boolean expect) {
        //given
        Score dealerScore = new Score(dealerCardBundle);
        Score gamblerScore = new Score(gamblerCardBundle);

        GameResultStrategy drawStrategy = new DrawStrategy();

        //when
        boolean actual = drawStrategy.enough(dealerScore, gamblerScore);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("순수 점수 비교 결과가 0인경우 무승부")
    @ParameterizedTest
    @CsvSource(value = {"1,false", "0,true", "-1,false"})
    void isMatch(int compare, boolean expect) {
        //given
        GameResultStrategy drawStrategy = new DrawStrategy();

        //when
        boolean actual = drawStrategy.isMatch(compare);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
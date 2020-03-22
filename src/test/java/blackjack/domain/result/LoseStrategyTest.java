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

class LoseStrategyTest {

    private static Stream<Arguments> loseEnoughProvider() {
        return Stream.of(
                Arguments.of("딜러 버스트o, 갬블러 버스트 o", aCardBundle(KING, KING, KING), aCardBundle(KING, KING, KING), false),
                Arguments.of("딜러 버스트o, 갬블러 버스트 x", aCardBundle(KING, KING, KING), aCardBundle(KING, KING), false),
                Arguments.of("딜러 버스트o, 갬블러 블랙잭 o", aCardBundle(KING, KING, KING), aCardBundle(KING, ACE), false),

                Arguments.of("딜러 버스트x, 갬블러 버스트 o", aCardBundle(KING, KING), aCardBundle(KING, KING, KING), true),
                Arguments.of("딜러 버스트x, 갬블러 버스트 x", aCardBundle(KING, KING), aCardBundle(KING, KING), false),
                Arguments.of("딜러 버스트x, 갬블러 블랙잭 o", aCardBundle(KING, KING), aCardBundle(KING, ACE), false),

                Arguments.of("딜러 블랙잭o, 갬블러 버스트 o", aCardBundle(KING, ACE), aCardBundle(KING, KING, KING), true),
                Arguments.of("딜러 블랙잭o, 갬블러 버스트 x", aCardBundle(KING, ACE), aCardBundle(KING, KING), true),
                Arguments.of("딜러 블랙잭o, 갬블러 블랙잭 o", aCardBundle(KING, ACE), aCardBundle(KING, ACE), false)
        );
    }

    @DisplayName("무조건 패배 조건, 갬블러만 버스트 or 딜러만 블랙잭인 경우")
    @ParameterizedTest(name = "[{0}] : {3}")
    @MethodSource("loseEnoughProvider")
    void enough(String header, CardBundle dealerCardBundle, CardBundle gamblerCardBundle, boolean expect) {
        //given
        GameResultStrategy loseStrategy = new LoseStrategy();
        Score gamblerScore = new Score(gamblerCardBundle);
        Score dealerScore = new Score(dealerCardBundle);

        //when
        boolean actual = loseStrategy.enough(dealerScore, gamblerScore);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("순수 점수 비교를 통해 얻은 결과가 -1이 패배")
    @ParameterizedTest
    @CsvSource(value = {"1,false", "0,false", "-1,true"})
    void isMatch(int compare, boolean expect) {
        //given
        GameResultStrategy loseStrategy = new LoseStrategy();

        //when
        boolean actual = loseStrategy.isMatch(compare);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
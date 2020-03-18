package blackjack.domain.result;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;

class WinStrategyTest {

    private static Stream<Arguments> winEnoughProvider() {
        return Stream.of(
                Arguments.of("딜러 버스트o, 갬블러 버스트o", aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), true),
                Arguments.of("딜러 버스트o, 갬블러 버스트x", aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), aCardBundle(), true),
                Arguments.of("딜러 버스트o, 갬블러 블랙잭o", aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), aCardBundle(CardNumber.KING), true),
                Arguments.of("딜러 버스트o, 갬블러 블랙잭x", aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), aCardBundle(CardNumber.KING, CardNumber.ACE), true),

                Arguments.of("딜러 버스트x, 갬블러 버스트o", aCardBundle(CardNumber.KING), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), false),
                Arguments.of("딜러 버스트x, 갬블러 버스트x", aCardBundle(CardNumber.KING), aCardBundle(CardNumber.KING), false),
                Arguments.of("딜러 버스트x, 갬블러 블랙잭o", aCardBundle(CardNumber.KING), aCardBundle(CardNumber.ACE, CardNumber.KING), true),
                Arguments.of("딜러 버스트x, 갬블러 블랙잭x", aCardBundle(CardNumber.KING), aCardBundle(CardNumber.KING), false),

                Arguments.of("딜러 블랙잭o, 갬블러 버스트o", aCardBundle(CardNumber.KING, CardNumber.ACE), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), false),
                Arguments.of("딜러 블랙잭o, 갬블러 버스트x", aCardBundle(CardNumber.KING, CardNumber.ACE), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), false),
                Arguments.of("딜러 블랙잭o, 갬블러 블랙잭o", aCardBundle(CardNumber.KING, CardNumber.ACE), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), false),
                Arguments.of("딜러 블랙잭o, 갬블러 블랙잭x", aCardBundle(CardNumber.KING, CardNumber.ACE), aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.KING), false)
        );
    }

    @DisplayName("무조건 우승 조건, 갬블러만 블랙잭이거나, 딜러가 버스트거나")
    @ParameterizedTest(name = "[{0}] : {3}")
    @MethodSource("winEnoughProvider")
    void enough(String header, CardBundle dealerCardBundle, CardBundle gamblerCardBundle, boolean expect) {
        //given
        GameResultStrategy winStrategy = new WinStrategy();

        //when
        boolean actual = winStrategy.enough(gamblerCardBundle, dealerCardBundle);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("순수 점수 비교를 통한 결과가 1인 경우 승")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "0,false", "-1,false"})
    void isMatch(int compare, boolean expect) {
        //given
        GameResultStrategy winStrategy = new WinStrategy();

        //when
        boolean actual = winStrategy.isMatch(compare);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
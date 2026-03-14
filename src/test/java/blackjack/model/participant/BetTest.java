package blackjack.model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @Nested
    class 문자열을_기반으로_생성된다 {
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "a1", "1a", "123 ", " 123", " 123 "})
        void 베팅_금액이_숫자가_아니라면_예외를_던진다(String illegalAmount) {
            assertThatThrownBy(() -> Bet.from(illegalAmount))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"100", "5000", "30000"})
        void 베팅_금액이_숫자라면_정상적으로_생성된다(String stringAmount) {
            assertThatCode(() -> Bet.from(stringAmount))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class 베팅_금액을_검증한다 {
        @ParameterizedTest
        @ValueSource(ints = {0, -1000, Integer.MIN_VALUE})
        void 베팅_금액이_0_이하라면_예외를_던진다(int notPositiveAmount) {
            assertThatThrownBy(() -> new Bet(notPositiveAmount))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 10, 150, 1234})
        void 베팅_금액이_100으로_나누어_떨어지지_않는다면_예외를_던진다(int notDivisibleAmount) {
            assertThatThrownBy(() -> new Bet(notDivisibleAmount))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {100, 1000, 5000})
        void 베팅_금액이_0보다_크고_100의_배수라면_정상적으로_생성된다(int positiveAmount) {
            assertThatCode(() -> new Bet(positiveAmount))
                    .doesNotThrowAnyException();
        }
    }
}

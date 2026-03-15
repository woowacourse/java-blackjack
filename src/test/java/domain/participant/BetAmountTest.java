package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 양수_숫자면_배팅_금액이_정상_생성된다(String input, int expected) {

                // when
                BetAmount actual = new BetAmount(input);

                // then
                assertThat(actual.getBetAmount()).isEqualTo(expected);
            }

            static Stream<Arguments> successCases() {
                return Stream.of(
                        Arguments.of("1", 1),
                        Arguments.of("1000", 1000),
                        Arguments.of("50000", 50000)
                );
            }
        }

        @Nested
        class Fail {

            @ParameterizedTest
            @ValueSource(strings = {"abc", "1a", "12 3", "1.5"})
            void 숫자가_아니면_예외가_발생한다(String input) {

                // when & then
                assertThatThrownBy(() -> new BetAmount(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BetAmount.INVALID_BET_AMOUNT_NUMBER);
            }

            @ParameterizedTest
            @ValueSource(strings = {"0", "-1", "-1000"})
            void 제로_이하이면_예외가_발생한다(String input) {

                // when & then
                assertThatThrownBy(() -> new BetAmount(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BetAmount.INVALID_BET_AMOUNT_POSITIVE);
            }
        }
    }
}

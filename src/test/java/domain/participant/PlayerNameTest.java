package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.BlackjackException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @ParameterizedTest
            @MethodSource("successCases")
            void 이름이_2글자_이상_5글자_이하면_정상_생성된다(String input) {

                // when
                PlayerName actual = new PlayerName(input);

                // then
                assertThat(actual.name()).isEqualTo(input);
            }

            static Stream<Arguments> successCases() {
                return Stream.of(
                        Arguments.of("ab"),
                        Arguments.of("abcde"),
                        Arguments.of("성 열")
                );
            }
        }

        @Nested
        class Fail {

            @ParameterizedTest
            @ValueSource(strings = {"a", "abcdef", "aa aaa"})
            void 이름_길이가_2글자_미만_또는_5글자_초과면_예외가_발생한다(String input) {

                // when & then
                assertThatThrownBy(() -> new PlayerName(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + PlayerName.PLAYER_NAME_LENGTH_OUT_OF_RANGE);
            }

            @ParameterizedTest
            @ValueSource(strings = {"", " ", "   "})
            void 이름이_공백이면_예외가_발생한다(String input) {

                // when & then
                assertThatThrownBy(() -> new PlayerName(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + PlayerName.PLAYER_NAME_BLANK);
            }
        }
    }
}

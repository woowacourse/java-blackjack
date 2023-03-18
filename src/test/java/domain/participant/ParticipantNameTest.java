package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantNameTest {

    @Nested
    @DisplayName("create() 테스트")
    class CreateStaticMethodTest {

        @ParameterizedTest(name = "파라미터로 입력된 name이 유효하면, 예외가 발생하지 않는다")
        @ValueSource(strings = {"1", "abcdeabcdeabcdeabcde"})
        void create_givenValidName_thenSuccess(final String validName) {
            final ParticipantName participantName = assertDoesNotThrow(() -> ParticipantName.create(validName));

            assertThat(participantName)
                    .isExactlyInstanceOf(ParticipantName.class);
        }

        @ParameterizedTest(name = "파라미터로 입력된 이름이 null이면 예외가 발생한다")
        @NullSource
        void create_givenNull_thenFail(final String invalidName) {
            assertThatThrownBy(() -> ParticipantName.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 Null일 수 없습니다.");
        }

        @ParameterizedTest(name = "파라미터로 입력된 이름이 비어 있으면 예외가 발생한다")
        @EmptySource
        void create_givenEmptyName_thenFail(final String invalidName) {
            assertThatThrownBy(() -> ParticipantName.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 공백일 수 없습니다.");
        }

        @ParameterizedTest(name = "파라미터로 입력된 name의 길이가 1~20글자 사이가 아니라면 예외가 발생한다")
        @ValueSource(strings = {"abcdeabcdeabcdeabcdea"})
        void create_givenLongName_thenFail(final String invalidName) {
            assertThatThrownBy(() -> ParticipantName.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 1글자에서 20글자 사이여야 합니다.");
        }
    }
}

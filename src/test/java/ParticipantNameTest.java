import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantNameTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("생성자에 null이나 빈 문자열이 입력되었을 경우 예외처리")
    void checkNullOrEmpty_NullOrEmpty_ExceptionThrown(String input) {
        assertThatThrownBy(() -> new ParticipantName(input))
                .isInstanceOf(InvalidParticipantNameException.class)
                .hasMessage(InvalidParticipantNameException.NULL_OR_EMPTY);
    }
}

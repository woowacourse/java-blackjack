package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    @DisplayName("참가자 수가 16명인 경우, 정상 동작한다.")
    void 참가자_수_정상_범위_테스트() {
        // given
        List<String> participantsName = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p");

        // when & then
        assertThatCode(() -> Participants.validateParticipantsNumbers(participantsName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 수가 17명인 경우, IllegalArgumentException이 발생한다.")
    void 참가자_수_초과_예외_테스트() {
        // given
        List<String> participantsName = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q");

        // when & then
        assertThatThrownBy(() ->
                Participants.validateParticipantsNumbers(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}

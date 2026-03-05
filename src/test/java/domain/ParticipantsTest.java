package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
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

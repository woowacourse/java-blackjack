package domain.participant;

import domain.participant.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @DisplayName("최대 8명 입력받는다.")
    @Test
    void validateMaxSize() {
        List<String> names = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사람은 최소 2명")
    @Test
    void validateMinSize() {
        List<String> names = List.of("one");

        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사람 이름은 중복이 될 수 없다.")
    @Test
    void validateDuplicateNames() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }
}

import domain.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {
    @Test
    @DisplayName("최대 8명 입력받는다.")
    void validateMaxSize() {
        //given
        List<String> names = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        //when
        //then
        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("사람은 최소 2명")
    void validateMinSize() {
        //given
        List<String> names = List.of("one");
        //when
        //then
        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("사람 이름은 중복이 될 수 없다.")
    void methodName() throws Exception {
        //given
        List<String> names = List.of("pobi", "pobi");
        //when
        //then
        assertThatThrownBy(() -> new Participants(names)).isInstanceOf(IllegalArgumentException.class);
    }
}

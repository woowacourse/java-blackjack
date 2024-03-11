package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;

    @DisplayName("9명 이상 입력할 경우 예외를 발생시킨다.")
    @Test
    void validateMaxSize() {
        List<String> names = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
    }

    @DisplayName("1명 이하 입력할 경우 예외를 발생시킨다.")
    @Test
    void validateMinSize() {
        List<String> names = List.of("one");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));;
    }

    @DisplayName("중복된 이름이 있을 경우 예외를 발생시킨다.")
    @Test
    void validateDuplicateNames() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }
}

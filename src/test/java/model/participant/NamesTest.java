package model.participant;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import model.participant.Names;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class NamesTest {
    @Test
    @DisplayName("중복된 플레이어의 이름이 포함된 목록은 예외를 발생한다.")
    void throwExceptionWhenDuplicatedNameInput() {

        List<String> value = List.of("도비", "도비", "프람");

        assertThrows(IllegalArgumentException.class, () -> new Names(value));
    }

    @Test
    @DisplayName("참가자의 이름으로 아무것도 입력 되지 않으면 예외를 발생시킨다.")
    void validate_ShouldThrowException_WhenInputEmptyPlayerName() {
        List<String> value = Collections.emptyList();

        assertThrows(IllegalArgumentException.class, () -> new Names(value));
    }
}

